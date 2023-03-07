package projetHopital.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import projetHopital.model.Compte;
import projetHopital.model.Medecin;
import projetHopital.model.Patient;
import projetHopital.model.Visite;

public class DaoVisiteImpl implements DaoVisite{

	@Override
	public void insert(Visite obj) {
		PreparedStatement ps=null;
		try {
			ps=Context.getContext().getConnection().prepareStatement(
					"insert into visite(numeroVisite, idPatient, idMedecin, coutVisite, salle, dateVisite) values(?,?,?,?,?,?)");
			ps.setInt(1, obj.getNumeroVisite());
			ps.setInt(2, obj.getPatient().getIdPatient());
			ps.setInt(3, obj.getMedecin().getIdCompte());
			ps.setInt(4, obj.getCoutVisite());
			ps.setInt(5, obj.getSalle());
			ps.setDate(6, Date.valueOf(obj.getDateVisite()));
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
	public void update(Visite obj) {
		PreparedStatement ps =null;
		//@formatter=off
		try {
			ps=Context.getContext().getConnection().prepareStatement(
					"update from visite set idPatient=?, "
											+ "idMedecin=?, "
											+ "coutVisite=?, "
											+ "salle=?, "
											+ "dateVisite=? "
											+ "where numeroVisite =?");
			ps.setInt(1, obj.getPatient().getIdPatient());
			ps.setInt(2, obj.getMedecin().getIdCompte());
			ps.setInt(3, obj.getCoutVisite());
			ps.setInt(4, obj.getSalle());
			ps.setDate(5, Date.valueOf(obj.getDateVisite()));
			ps.setInt(6, obj.getNumeroVisite());
			ps.executeUpdate();
			ps.close();
		//@formatter:on
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(ps);
			Context.destroy();
		}
		
	}


//	}
//
//	
//	private Visite getVisite(ResultSet rs) throws SQLException{
//		Patient patient=getPatient(rs);
//		Visite viste=new Visite(rs.getInt("numeroVisite"), patient.getIdPatient(), null, rs.getInt("coutVisite"), rs.getInt("salle"), rs.getDate("dateVisite"));
//		
//	}
	
	
	
	@Override
	public void delete(Visite obj) {
		deleteByKey(obj.getNumeroVisite());
		
	}

	@Override
	public void deleteByKey(Integer key) {
		PreparedStatement ps = null;
		try {
			ps=Context.getContext().getConnection().prepareStatement(
					"delete from visite where numeroVisite=?");
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


	public Patient getPatient(ResultSet rs) throws SQLException {
	Patient patient=new Patient(rs.getInt("idPatient"), rs.getString("nomPatient"), rs.getString("prenomPatient"));
	return patient;
	}
	public Medecin getMedecin(ResultSet rs) throws SQLException {
	Medecin medecin=new Medecin(rs.getInt("idCompte"), rs.getString("login"), rs.getString("password"), rs.getString("typeCompte"));
	return medecin;
	}
	

	
	@Override
	public Visite findByKey(Integer key) {
		//@formatter:off
		Visite visite=null;
		PreparedStatement ps=null;
		try {
			ps=Context.getContext().getConnection().prepareStatement("select v.numeroVisite, p.idPatient, c.idCompte, v.coutVisite, v.salle, v.dateVisite "
																		+ "from visite v "
																		+ "left join patient p "
																		+ "on v.idPatient=p.idPatient "
																		+ "left join compte c "
																		+ "on v.idCompte=c.idCompte"
																		+ "where numeroVisite=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				visite=new Visite(
								rs.getInt("numeroVisite"), 
								null, 
								null, 
								rs.getInt("coutVisite"),
								rs.getInt("salle"), 
								rs.getDate("dateVisite").toLocalDate());
				Patient patient=getPatient(rs);
				visite.setPatient(patient);
				Medecin medecin = getMedecin(rs);
				visite.setMedecin(medecin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(ps);
			Context.destroy();
		}
		return visite;
		//@formatter:on
	}

	@Override
	public List<Visite> findAll() {
		List<Visite> visites = new ArrayList<>();
		Visite visite=null;
		Statement st=null;
		try {
			st=Context.getContext().getConnection().createStatement();
			ResultSet rs=st.executeQuery("select v.numeroVisite, p.idPatient, c.idCompte, v.coutVisite, v.salle, v.dateVisite "
					+ "from visite v "
					+ "left join patient p "
					+ "on v.idPatient=p.idPatient "
					+ "left join compte c "
					+ "on v.idCompte=c.idCompte ");
			while(rs.next()) {
				visite=new Visite(
						rs.getInt("numeroVisite"), 
						null, 
						null, 
						rs.getInt("coutVisite"),
						rs.getInt("salle"), 
						rs.getDate("dateVisite").toLocalDate());
				Patient patient=getPatient(rs);
				visite.setPatient(patient);
				Medecin medecin = getMedecin(rs);
				visite.setMedecin(medecin);
				visites.add(visite);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Closer.close(st);
			Context.destroy();
		}
		return visites;
	}

}
