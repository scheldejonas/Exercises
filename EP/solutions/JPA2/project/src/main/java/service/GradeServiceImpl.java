package service;

import dao.GradeDao;
import dao.GradeDaoImpl;
import dao.PersonDao;
import dao.PersonDaoImpl;
import domain.Grade;

/**
 * Created by schelde on 09/06/17.
 */
public class GradeServiceImpl implements GradeService {
    private static final GradeService singleton = new GradeServiceImpl();
    private GradeDao gradeDao = GradeDaoImpl.getSingleton();

    private GradeServiceImpl() {
    }

    public static GradeService getSingleton() {
        return singleton;
    }

    @Override
    public void createGrade(Grade grade) {
        gradeDao.create(grade);
    }

    @Override
    public Grade findByName(String phd) {
        return gradeDao.findByName(phd);
    }
}
