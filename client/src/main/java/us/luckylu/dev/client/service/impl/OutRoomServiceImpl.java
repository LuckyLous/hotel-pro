package us.luckylu.dev.client.service.impl;

import org.springframework.stereotype.Service;

/**
 * @author lu
 * @create 2019-03-22 17:44
 */
@Service
public class OutRoomServiceImpl {

    public void create(){
        String sql = "UPDATE inroom SET ir_status = ?, deposit = 0 WHERE ir_id = ?";

        sql = "INSERT INTO outroom(ir_id,or_date,room_pay,fine_pay,pay_method) VALUES(?,Now(),?,?,?)";

        sql = "UPDATE client SET cli_status = ?,advance_pay= 0  WHERE cli_id = ?";

        sql = "UPDATE room SET room_status = ? WHERE room_id=?";
    }

    public void list(int year,int month){
        StringBuffer sb = new StringBuffer("SELECT or_id,or_date,room_pay,fine_pay,pay_method FROM outroom ");
        if(year != 0 )
            sb.append( "AND Year(or_date) = " + year);
        // �����·ݲ�ѯ
        if( month != 0)
            sb.append(" AND Month(or_date) =  "+ month);

        sb.append(" ORDER BY or_date ");
        sb.toString().replaceFirst("AND", "WHERE");
    }
}
