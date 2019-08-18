package us.luckylu.dev.client.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * ����תΪ�ַ���ʾ
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/**
	 * ת���ַ�ʱ��Ϊ���ڱ���
	 */
	public static Date dateParse(String date) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd HH").parse(date);
	}
	
	/**
	 * ����SQL�洢��ʱ��(ȥ��ʱ����)
	 * @param date
	 * @return
	 */
	public static java.sql.Date toSqlDate(Date date){
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * ����SQL�洢��ʱ���(����ʱ����)
	 * @param date
	 * @return
	 */
	public static Timestamp toTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
	
	/**
	 * �����������ڵ�ʱ���		
	 * @param nDate ������
	 * @param oDate ������
	 * @return
	 */
	public static int getIntervalDays(Date nDate, Date oDate) {
		if (null == nDate || null == oDate) {
			return -1;
		}
		
		long intervalMilli = nDate.getTime() - oDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	}
		
	
}
