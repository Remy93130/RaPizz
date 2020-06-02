package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.util.Database;

public abstract class AbstactDao {
    protected final Database db;

    protected AbstactDao() {
        this.db = Database.getDatabase();
    }
}
