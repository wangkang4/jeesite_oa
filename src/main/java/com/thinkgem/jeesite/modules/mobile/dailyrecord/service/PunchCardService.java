package com.thinkgem.jeesite.modules.mobile.dailyrecord.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.dao.PunchCardDao;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.PunchCard;
import com.thinkgem.jeesite.modules.mobile.dailyrecord.entity.PunchCardbe;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PunchCardService extends CrudService<PunchCardDao, PunchCard> {

    @Autowired
    private PunchCardDao punchCardDao;

    public List<PunchCard> findPunchCards(String userid) {
        User user = new User ();
        user.setId ( userid );
        PunchCard pCard = new PunchCard ();
        pCard.setUser ( user );

        Calendar cal = Calendar.getInstance ();

        cal.set ( Calendar.HOUR_OF_DAY, 6 );

        cal.set ( Calendar.SECOND, 0 );

        cal.set ( Calendar.MINUTE, 0 );

        Date standDate = new Date ( cal.getTimeInMillis () );

        pCard.setStartTime ( standDate );
        pCard.setEndTime ( new Date () );

        return punchCardDao.findPunchCards ( pCard );
    }

    @Transactional(readOnly = false)
    public PunchCardbe savePunchCards(String userid, PunchCard pCard) {
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd
        // HH:mm:ss");
        PunchCard pCard1 = new PunchCard ();

        Calendar cal = Calendar.getInstance ();

        cal.set ( Calendar.HOUR_OF_DAY, 9 );

        cal.set ( Calendar.SECOND, 0 );

        cal.set ( Calendar.MINUTE, 0 );

        Date goDate = new Date ( cal.getTimeInMillis () );

        cal.set ( Calendar.HOUR_OF_DAY, 18 );

        cal.set ( Calendar.SECOND, 0 );

        cal.set ( Calendar.MINUTE, 0 );

        Date backDate = new Date ( cal.getTimeInMillis () );

        User user = new User ();
        user.setId ( userid );
        pCard.setUser ( user );

        Date now = new Date ();
        pCard.setPunchDate ( now );
        int flag = 1;
        if (StringUtils.isNotBlank ( pCard.getPunchType () )) {
            if (pCard.getPunchType ().equals ( "0" )) {
                if (now.after ( goDate )) {
                    pCard.setState ( "迟到" );
                } else {
                    pCard.setState ( "正常" );
                }
            } else if (pCard.getPunchType ().equals ( "1" )) {
                cal.set ( Calendar.HOUR_OF_DAY, 6 );

                cal.set ( Calendar.SECOND, 0 );

                cal.set ( Calendar.MINUTE, 0 );

                Date standDate = new Date ( cal.getTimeInMillis () );

                pCard.setStartTime ( standDate );
                pCard.setEndTime ( now );
                if (pCard.getPunchTimeType ().equals ( "0" )) {
                    pCard1 = punchCardDao.findPunchCard ( pCard );
                } else if (pCard.getPunchTimeType ().equals ( "1" )) {
                    List<PunchCard> pCardList = punchCardDao.findPunchCards ( pCard );
                    pCard1 = pCardList.get ( 0 );
                } else {
                    flag = -1;
                }

                if (backDate.after ( now )) {
                    pCard.setState ( "早退" );
                } else {
                    pCard.setState ( "正常" );
                }
            } else {
                flag = -1;
            }
        } else {
            flag = -2;
        }

        if (StringUtils.isNotBlank ( pCard.getPunchTimeType () )) {
            if (pCard.getPunchTimeType ().equals ( "0" )) {
                pCard.preInsert ();
                flag = punchCardDao.insertOnePCard ( pCard );
            } else if (pCard.getPunchTimeType ().equals ( "1" )) {
                pCard.preUpdate ();
                flag = punchCardDao.updateOnePCard ( pCard );
            } else {
                flag = -1;
            }
        } else {
            flag = -2;
        }
        PunchCardbe pCardb = new PunchCardbe ();
        pCardb.setFlag ( flag );
        pCardb.setpCard ( pCard );
        pCardb.setpCardbefore ( pCard1 );

        return pCardb;
    }

}
