package dao;

import domain.Grade;

/**
 * Created by schelde on 09/06/17.
 */
public interface GradeDao {
    Grade findByName(String phd);

    void create(Grade grade);
}
