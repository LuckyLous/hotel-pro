package us.luckylu.dev.client.dao;

import java.sql.SQLException;

public class InRoomDao {
	/*
	*//**
	 * ��ӿ�����¼
	 * @param inRoom
	 * @return
	 *//*
	public boolean addInRoom(InRoom inRoom){
		Connection conn = null;
		PreparedStatement ps = null;
				
		try {
			conn = DbUtil.getConnection();
			DbUtil.setAutoCommit(conn, false);

			// ��������(��ס��Ԥ��)
			String sql = "";
			
			// ���¿ͷ�״̬
			sql = "";
			ps = conn.prepareStatement(sql);
			if(InRoom.NOW_ORDER == inRoom.getInRoomType()){
				// 1�������Ԥ���Ļ�
				ps.setInt(1, Room.ORDERING);
			}else{
				// 2���������ס�Ļ�
				ps.setInt(1, Room.USING);
			}
			ps.setInt(2, inRoom.getRoom().getRoomId());
			ps.executeUpdate();
					
			// ���¿ͻ�״̬,Ԥ���� 

			ps = conn.prepareStatement(sql);
			if(InRoom.NOW_ORDER == inRoom.getInRoomType()){
				// 1�������Ԥ���Ļ�
				ps.setInt(1, Client.ORDERING);
			}else{
				// 2���������ס�Ļ�
				ps.setInt(1, Client.HOUSING);
			}
			
			ps.setDouble(2, inRoom.getClient().getAdvancePay());
			ps.setInt(3, inRoom.getClient().getCliId());
			ps.executeUpdate();
							
			
			DbUtil.commit(conn);
			return  true;
		} catch (SQLException e) {
			e.printStackTrace();
			DbUtil.rollback(conn);
		} finally{
			DbUtil.setAutoCommit(conn, true);
			DbUtil.close(conn, ps, null);
		}
		return false;
	}
	
	
	*//**
	 * ��ȡ��Ӧ�ͷ��۸�
	 *//*
	public Double getPrice(int roomId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			conn = DbUtil.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT rt_price AS deposit FROM roomtype rt ")
			.append(" JOIN room  r  ON r.rt_id = rt.rt_id ")
			.append(" AND room_id = ? ");
			
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, roomId);
			rs = ps.executeQuery();
			if(rs.next()){
				 return rs.getDouble("deposit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtil.close(conn, ps, null);
		}
		return null;
	}
	
	*//**
	 * ��ȡ����id�е����пͻ�id�Ϳͷ�id
	 * @param inroomId
	 * @return
	 *//*
	public Map<String,Integer> getAllId(int inroomId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String,Integer> map = new HashMap<String, Integer>();
		
		try {
			conn = DbUtil.getConnection();
			String sql = "SELECT cli_id,room_id FROM inroom WHERE ir_id = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inroomId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				map.put("cliId", rs.getInt("cli_id"));
				map.put("roomId", rs.getInt("room_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			DbUtil.close(conn, ps, rs);
		}
		return map;
	}
	
	*//**
	 * �����������˷�����ʱͬ����ʾ
	 *//*
	public Map<String,Double> getTotalMoney(int inroomId){
		// ����Ӧ����� = �ͷ���� �� ���ڽ��(�����)
		// �Ա����ս�� = Ԥ���� + Ѻ�𣬶����ٲ�
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		int overStatus = 0;
		Map<String,Double> map = new HashMap<String,Double>();
		Double roomPay = null ;
		Double finePay = null;
		try {
			conn = DbUtil.getConnection();
			DbUtil.setAutoCommit(conn, false);
			
			*//*
			 * �ڽ������modifyOverStatus�����ﲻ��д
			 * // ��ȡ�������
			String sql = "SELECT getInroomStatus(?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inroomId);
			rs = ps.executeQuery();
			if(rs.next()){
				overStatus = rs.getInt(1);
			}
			// ����ס�޵ĳ���״̬
			sql = "UPDATE inroom SET overdue_status = ? WHERE ir_id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, overStatus);
			ps.setInt(2, inroomId);
			ps.executeUpdate();*//*
			
			// ��ȡ�ͷ����ͳ��ڽ��
			String sql = "SELECT getRoomMoney(?),getFineMoney(?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inroomId);
			ps.setInt(2, inroomId);
			rs = ps.executeQuery();
			if(rs.next()){
				roomPay = rs.getDouble(1);
				finePay = rs.getDouble(2);
			}
			map.put("roomPay", roomPay);
			map.put("finePay", finePay);
			
			DbUtil.commit(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			DbUtil.rollback(conn);
		} finally{
			DbUtil.setAutoCommit(conn, true);
			DbUtil.close(conn, ps, null);
		}
		return map;
		
	}
	
	
	*//**
	 * ȷ����ס�����Ԥ���ͻ�
	 *//*
	public boolean checkInRoom(InRoom inRoom){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DbUtil.getConnection();		
			DbUtil.setAutoCommit(conn, false);
			// ���¿���״̬Ϊ��ס��¼������Ԥ�Ƶ���ʱ�䣬����Ѻ��
			String sql  = "UPDATE inroom SET ir_status = ?,expe_arrivetime=Now(),deposit=? WHERE ir_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, InRoom.HOUSING_RECORD);
			ps.setDouble(2, inRoom.getDeposit());
			ps.setInt(3, inRoom.getInRoomId());
			ps.executeUpdate();
			// ���¿ͷ�״̬
			sql = "UPDATE room SET room_status = ? WHERE room_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Room.USING);
			ps.setInt(2, inRoom.getRoom().getRoomId());
			ps.executeUpdate();					
			// ���¿ͻ�״̬
			sql = "UPDATE client SET cli_status = ? WHERE cli_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Client.HOUSING);
			ps.setInt(2, inRoom.getClient().getCliId());
			ps.executeUpdate();
			
			DbUtil.commit(conn);
			return  true;
		} catch (SQLException e) {
			e.printStackTrace();
			DbUtil.rollback(conn);
		} finally{
			DbUtil.setAutoCommit(conn, true);
			DbUtil.close(conn, ps, null);
		}
		return false;
		
	}
	
	*//**
	 * ������ס�����Ԥ���ͻ�
	 * Ӱ�쵽�ͻ�״̬�Ϳͷ�״̬
	 *//*
	public boolean cancelInroom(InRoom inRoom){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DbUtil.getConnection();		
			DbUtil.setAutoCommit(conn, false);
			// ���¿���״̬Ϊ��ʷ��¼
			String sql  = "UPDATE inroom SET ir_status = ? WHERE ir_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, InRoom.HISTORY_RECORD);
			ps.setInt(2, inRoom.getInRoomId());
			ps.executeUpdate();
			// ���¿ͷ�״̬
			sql = "UPDATE room SET room_status = ? WHERE room_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Room.AVAILABLE);
			ps.setInt(2, inRoom.getRoom().getRoomId());
			ps.executeUpdate();					
			// ���¿ͻ�״̬�����Ԥ����
			sql = "UPDATE client SET cli_status = ?,advance_pay=0 WHERE cli_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Client.LEAVED);
			ps.setInt(2, inRoom.getClient().getCliId());
			ps.executeUpdate();
			
			DbUtil.commit(conn);
			return  true;
		} catch (SQLException e) {
			e.printStackTrace();
			DbUtil.rollback(conn);
		} finally{
			DbUtil.setAutoCommit(conn, true);
			DbUtil.close(conn, ps, null);
		}
		return false;
		
	}
	
	*//**
	 * �ͷ�����
	 * @param inRoomId
	 * @param extendDays
	 *//*
	public boolean addExtendDays(int inRoomId, int extendDays){
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DbUtil.getConnection();		
			String sql = "UPDATE inroom SET extend_days = (extend_days+?),expe_leavetime = ADDDATE(expe_leavetime,INTERVAL ? DAY) WHERE ir_id = ?";
			ps = conn.prepareStatement(sql);	
			ps.setInt(1, extendDays);
			ps.setInt(2, extendDays);
			ps.setInt(3, inRoomId);
			
			return  DbUtil.executeUpdate(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtil.close(conn, ps, null);
		}
		return false;
	}
	
	*//**
	 * ���³���״̬
	 * @param inroomId
	 *//*
	public void modifyOverStatus(int inroomId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int overStatus = 0;
		try{
			conn = DbUtil.getConnection();		
			DbUtil.setAutoCommit(conn, false);
			// ��ȡ�������
			String sql = "SELECT getInroomStatus(?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inroomId);
			rs = ps.executeQuery();
			if(rs.next()){
				overStatus = rs.getInt(1);
			}
			// ����ס�޵ĳ���״̬
			sql = "UPDATE inroom SET overdue_status = ? WHERE ir_id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, overStatus);
			ps.setInt(2, inroomId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			DbUtil.rollback(conn);
		} finally{
			DbUtil.setAutoCommit(conn, true);
			DbUtil.close(conn, ps, null);
		}
	}
	
	*//**
	 * ��ѯ������¼�б�
	 * �ֱ���Ԥ��״̬����ס״̬��ʾ
	 * �ɰ��տ���״̬����������,�ͻ�֤����(��ȷƥ��)���ͻ��ֻ�Ų�ѯ
	 *//*
	public List<InRoom> list(InRoom inRoom){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<InRoom> inRoomList = new ArrayList<InRoom>();
		
		try {
			conn = DbUtil.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT ir_id,ir_type,ir_status,ir_date,cli_name,cli_phone,card_number,advance_pay,room_name,rt_name,rt_price,expe_arrivetime,expe_leavetime,deposit,extend_days,overdue_status ")
			.append(" FROM inroom ir, room r,roomtype rt,client c ")
			.append(" WHERE ir.room_id = r.room_id ")
			.append(" AND r.rt_id = rt.rt_id ")
			.append(" AND ir.cli_id = c.cli_id ");
 			
			// ���տ������Ͳ�ѯ
			if(inRoom.getInRoomType() != -1)
				sb.append(" AND ir_type = "+ inRoom.getInRoomType() +" ");
			// ���տ���״̬
			if(inRoom.getInRoomStatus() != -1)
				sb.append(" AND ir_status = "+ inRoom.getInRoomStatus()+"");
			// ���տͷ����ѯ
			if(inRoom.getRoom() != null && StringUtil.isNotEmpty(inRoom.getRoom().getRoomName()))
				sb.append(" AND room_name LIKE '%"+ inRoom.getRoom().getRoomName()+"%' ");
			//���տͻ�֤���Ų�ѯ
			if(inRoom.getClient() != null && StringUtil.isNotEmpty(inRoom.getClient().getCardNumber()))
				sb.append(" AND card_number = '"+ inRoom.getClient().getCardNumber() +"' ");
			//���տͻ��ֻ�Ų�ѯ	
			if(inRoom.getClient() != null && StringUtil.isNotEmpty(inRoom.getClient().getCliPhone()))
				sb.append(" AND cli_phone LIKE '%"+ inRoom.getClient().getCliPhone() +"%' ");
				
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			inRoomList = makeInRoomList(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			DbUtil.close(conn, ps, rs);
		}
		return inRoomList;
		
	}
	
	*//**
	 * ��ʷ��¼��ѯ
	 * @param year
	 * @param month
	 * @return
	 *//*
	public List<InRoom> historyList(InRoom inRoom,int year,int month){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<InRoom> historyList = new ArrayList<InRoom>();
		
		try {
			conn = DbUtil.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT ir_id,ir_date,ir_type,cli_name,cli_phone,room_name,rt_name,rt_price ")
			.append(" FROM inroom ir, room r,roomtype rt,client c ")
			.append(" WHERE ir.room_id = r.room_id ")
			.append(" AND r.rt_id = rt.rt_id ")
			.append(" AND ir.cli_id = c.cli_id ")
			.append(" AND ir_status = "+ inRoom.getInRoomStatus()+" ");
						
			// ������ݲ�ѯ
			if(year != 0){
				sb.append(" AND Year(ir_date) = " + year);
			}
			// �����·ݲ�ѯ
			if(month != 0){
				sb.append(" AND Month(ir_date) = " + month);
			}
			
			sb.append(" ORDER BY ir_date ");
							
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				InRoom inroom = new InRoom();
				
				int inRoomId = rs.getInt("ir_id");
				int inRoomType = rs.getInt("ir_type");
				Date inRoomDate = rs.getTimestamp("ir_date");
				String cliName = rs.getString("cli_name");
				String cliPhone = rs.getString("cli_phone");
				String roomName = rs.getString("room_name");
				String roomTypeName = rs.getString("rt_name");
				Double price = rs.getDouble("rt_price");
				
				Client client = new Client();
				client.setCliName(cliName);
				client.setCliPhone(cliPhone);
				
				RoomType roomType = new RoomType();
				roomType.setRoomTypeName(roomTypeName);
				roomType.setPrice(price);
				
				inroom.setClient(client);
				inroom.setRoom(new Room(roomType,roomName));
				inroom.setInRoomId(inRoomId);
				inroom.setInRoomType(inRoomType);
				inroom.setInRoomDate(inRoomDate);
				
				historyList.add(inroom);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			DbUtil.close(conn, ps, rs);
		}
		return historyList;
	}
	
	*//**
	 * ����������Ϣ����ͻ���Ϣ�Ϳͷ���Ϣ
	 * @throws SQLException 
	 *//*
	private List<InRoom> makeInRoomList(ResultSet rs) throws SQLException{
		List<InRoom> inRoomList = new ArrayList<InRoom>();
		while(rs.next()){
			InRoom inRoom = new InRoom();
			
			int inRoomId = rs.getInt("ir_id");
			int inRoomType = rs.getInt("ir_type");
			int inRoomStatus = rs.getInt("ir_status");
			Date inRoomDate = rs.getTimestamp("ir_date");
			Date expectedArriveTime = rs.getTimestamp("expe_arrivetime");
			Date expectedLeaveTime = rs.getTimestamp("expe_leavetime");
			Double deposit = rs.getDouble("deposit");
			int extendDays = rs.getInt("extend_days");
			int overDueStatus = rs.getInt("overdue_status");
			
			
			String cliName = rs.getString("cli_name");
			String cliPhone = rs.getString("cli_phone");
			String cardNumber = rs.getString("card_number");
			Double advancePay = rs.getDouble("advance_pay");
			
			String roomName = rs.getString("room_name");
			String roomTypeName = rs.getString("rt_name");
			Double price = rs.getDouble("rt_price");
			
			Client client = new Client();
			client.setCliName(cliName);
			client.setCliPhone(cliPhone);
			client.setCardNumber(cardNumber);
			client.setAdvancePay(advancePay);
					
			RoomType roomType = new RoomType();
			roomType.setRoomTypeName(roomTypeName);
			roomType.setPrice(price);
				
			inRoom.setClient(client);
			inRoom.setRoom(new Room(roomType,roomName));
			
			inRoom.setInRoomId(inRoomId);
			inRoom.setInRoomType(inRoomType);
			inRoom.setInRoomStatus(inRoomStatus);
			inRoom.setInRoomDate(inRoomDate);
			inRoom.setExpectedArriveTime(expectedArriveTime);
			inRoom.setExpectedLeaveTime(expectedLeaveTime);
			inRoom.setDeposit(deposit);
			inRoom.setExtendDays(extendDays);
			inRoom.setOverDueStatus(overDueStatus);
			
			inRoomList.add(inRoom);
		}
		return inRoomList;
	
	}*/
}
