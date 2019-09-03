package com.example.administrator.myp2p.gbean;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.administrator.myp2p.bean.DayQuestion;
import com.example.administrator.myp2p.bean.Question;

import com.example.administrator.myp2p.gbean.DayQuestionDao;
import com.example.administrator.myp2p.gbean.QuestionDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dayQuestionDaoConfig;
    private final DaoConfig questionDaoConfig;

    private final DayQuestionDao dayQuestionDao;
    private final QuestionDao questionDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dayQuestionDaoConfig = daoConfigMap.get(DayQuestionDao.class).clone();
        dayQuestionDaoConfig.initIdentityScope(type);

        questionDaoConfig = daoConfigMap.get(QuestionDao.class).clone();
        questionDaoConfig.initIdentityScope(type);

        dayQuestionDao = new DayQuestionDao(dayQuestionDaoConfig, this);
        questionDao = new QuestionDao(questionDaoConfig, this);

        registerDao(DayQuestion.class, dayQuestionDao);
        registerDao(Question.class, questionDao);
    }
    
    public void clear() {
        dayQuestionDaoConfig.clearIdentityScope();
        questionDaoConfig.clearIdentityScope();
    }

    public DayQuestionDao getDayQuestionDao() {
        return dayQuestionDao;
    }

    public QuestionDao getQuestionDao() {
        return questionDao;
    }

}