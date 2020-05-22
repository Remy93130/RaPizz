package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.util.Database;

public abstract class AbstactDao {
    protected final Database db;

    public AbstactDao() {
        this.db = Database.getDatabase();
    }
}
