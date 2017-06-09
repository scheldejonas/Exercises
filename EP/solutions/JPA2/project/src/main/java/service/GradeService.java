package service;

import domain.Grade;

/**
 * Created by schelde on 09/06/17.
 */
public interface GradeService {
    void createGrade(Grade grade);

    Grade findByName(String phd);
}
