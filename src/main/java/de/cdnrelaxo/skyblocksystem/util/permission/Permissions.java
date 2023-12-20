package de.cdnrelaxo.skyblocksystem.util.permission;

public enum Permissions {

    SETUP_COMMAND("skyblock.setup");

    final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String get() {
        return permission;
    }
}
