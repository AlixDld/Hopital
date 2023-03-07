package projetHopital.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projetHopital.model.Patient;

public class DaoPatientImpl implements DaoPatient{

	@Override
	public void insert(Patient obj) {
		PreparedStatement ps=null;
		try {
			ps=Context.getContext().getConnection().prepareStatement("insert into patient(idPatient,nomPatient,prenomPatient) values (?,?,?)");
			ps.setInt(1, obj.getIdPatient());
			ps.setString(2, obj.getNom());
			ps.setString(3, obj.getPrenom());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(ps);
			Context.destroy();
		}
		
		
	}

	@Override
	public void update(Patient obj) {
		PreparedStatement ps=null;
		try {
			ps=Context.getContext().getConnection().prepareStatement("update patient set nomPatient=?, prenomPatient=? where idPatient=?");
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setInt(3, obj.getIdPatient());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(ps);
			Context.destroy();
		}
		
		
	}

	@Override
	public void delete(Patient obj) {
		deleteByKey(obj.getIdPatient());
		
	}

	@Override
	public void deleteByKey(Integer key) {
		PreparedStatement ps=null;
		try {
			ps=Context.getContext().getConnection().prepareStatement("delete from patient where idPatient=?");
			ps.setInt(1, key);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(ps);
			Context.destroy();
		}	
	}

	
	private Patient getPatient(ResultSet rs)throws SQLException{
		// @formatter:off
		return new Patient(
						rs.getInt("idPatient"), 
						rs.getString("nomPatient"), 
						rs.getString("prenomPatient"));
		// @formatter:on
	}
	
	
	
	
	@Override
	public Patient findByKey(Integer key) {
		Patient patient=null;
		PreparedStatement ps=null;
		try {
			ps=Context.getContext().getConnection().prepareStatement("select * from patient where idPatient=?");
			ps.setInt(1, key);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				patient=getPatient(rs);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(ps);
			Context.destroy();
		}
		return patient;
	}

	@Override
	public List<Patient> findAll() {
		List<Patient> patients=new ArrayList<>();
		Patient patient=null;
		Statement st=null;
		try {
			st=Context.getContext().getConnection().createStatement();
			ResultSet rs=st.executeQuery("select * from patient");
			while(rs.next()) {
				patient=getPatient(rs);
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(st);
			Context.destroy();
		}
		
		return patients;
	}

}
