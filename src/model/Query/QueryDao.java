/*
 * Membres du projet :
 *
 * - FORTUNA Paul-Alexandre
 * - DING Armand
 * - FEKLER David
 * - CHANZY Alexandre
 * */

package model.Query;

import java.util.List;

public interface QueryDao {
    //Interface pour les méthodes d'exécution de requêtes et de statements
    public List<String> executeQuery(String query);
    public List<String> executeStatement(String statement);
}
