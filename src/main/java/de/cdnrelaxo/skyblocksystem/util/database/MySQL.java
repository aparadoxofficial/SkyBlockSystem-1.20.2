package de.cdnrelaxo.skyblocksystem.util.database;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
 * Writer: Mxscha
 */
public class MySQL {
    File file = new File("plugins//SkyBlockSystem", "mysql.yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    private MySQL mySQL;

    @SuppressWarnings("UnstableApiUsage")
    private final LoadingCache<Integer, Connection> cache = CacheBuilder
            .newBuilder().expireAfterAccess(10, TimeUnit.SECONDS)
            .removalListener((RemovalListener<Integer, Connection>) removalNotification -> {
                try {
                    if (removalNotification.getValue() != null) {
                        removalNotification.getValue().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).build(new CacheLoader<Integer, Connection>() {
                @Override
                public @NotNull Connection load(Integer integer) throws Exception {
                    // EndOfLifeCore.getInstance().getLogger().info("MYSQL Connection CREATED");
                    return createConnection();
                }
            });

    private String connectionUrl, database, user, password;
    private Integer port;

    private MySQL(String connectionUrl, String database, String user, String password, Integer port) {
        this.connectionUrl = connectionUrl;
        this.database = database;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public MySQL() {
    }

    public boolean update(String update, Object... objs) {
        try {
            Connection connection = cache.get(1);
            PreparedStatement p = connection.prepareStatement(update);
            setArguments(objs, p);
            boolean feedback = p.execute();
            p.close();
            return feedback;
        } catch (SQLException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet query(String query, Object... objs) {
        try {
            Connection connection = cache.get(1);
            PreparedStatement p = connection.prepareStatement(query);
            setArguments(objs, p);
            return p.executeQuery();
        } catch (SQLException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setArguments(Object[] objs, PreparedStatement p) throws SQLException {
        for (int i = 0; i < objs.length; i++) {
            Object obj = objs[i];
            if (obj instanceof String) {
                p.setString(i + 1, (String) obj);
            } else if (obj instanceof Integer) {
                p.setInt(i + 1, (Integer) obj);
            } else if (obj instanceof Date) {
                p.setDate(i + 1, (Date) obj);
            } else if (obj instanceof Timestamp) {
                p.setTimestamp(i + 1, (Timestamp) obj);
            } else if (obj instanceof Boolean) {
                p.setBoolean(i + 1, (Boolean) obj);
            } else if (obj instanceof Float) {
                p.setFloat(i + 1, (Float) obj);
            } else if (obj instanceof Double) {
                p.setDouble(i + 1, (Double) obj);
            } else if (obj instanceof Long) {
                p.setLong(i + 1, (Long) obj);
            }
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + connectionUrl + ":" + port + "/" + database + "?autoReconnect=true", user, password);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String connectionUrl, database, user, password;
        private Integer port;

        private Builder() {
        }

        public Builder withUrl(String url) {
            this.connectionUrl = url;
            return this;
        }

        public Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        public Builder withUser(String user) {
            this.user = user;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPort(Integer port) {
            this.port = port;
            return this;
        }

        public MySQL create() {
            Preconditions.checkNotNull(connectionUrl, "Connection URL is null");
            Preconditions.checkNotNull(database, "Database is null");
            Preconditions.checkNotNull(user, "Username is null");
            Preconditions.checkNotNull(password, "Password is null");
            Preconditions.checkNotNull(port, "Port is null");
            return new MySQL(connectionUrl, database, user, password, port);
        }
    }

    private void save() {
        if (!file.exists()) {
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initMySQLConfig() {
        if (!config.contains("MySQL.url")) {
            config.set("MySQL.url", "url");
            config.set("MySQL.port", "port");
            config.set("MySQL.database", "database");
            config.set("MySQL.user", "user");
            config.set("MySQL.password", "password");
        }
        save();
    }

    public MySQL get() {
        if (mySQL == null)
            connect();
        return mySQL;
    }

    private void connect() {
        initMySQLConfig();
        String url = "";
        int port = 0;
        String database = "";
        String user = "";
        String password = "";
        url = config.getString("MySQL.url");
        port = config.getInt("MySQL.port");
        database = config.getString("MySQL.database");
        user = config.getString("MySQL.user");
        password = config.getString("MySQL.password");
        if (password == null)
            password = "";
        this.mySQL = MySQL.newBuilder().withUrl(url).withPort(port).withDatabase(database).withUser(user).withPassword(password).create();
    }
}
