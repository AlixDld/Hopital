package projetHopital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import projetHopital.model.Compte;
import projetHopital.model.Medecin;
import projetHopital.model.Patient;
import projetHopital.model.Secretaire;

public class DaoCompteImpl implements DaoCompte {

  @Override
  public void insert(Compte obj) {
    PreparedStatement preparedStatement = null;
    Connection connection = null;
    try {
      connection = Context.getContext().getConnection();
      preparedStatement =
        connection.prepareStatement(
          "insert into compte(idCompte,login,password,typeCompte) values(?,?,?,?)"
        );
      preparedStatement.setInt(1, obj.getIdCompte());
      preparedStatement.setString(2, obj.getLogin());
      preparedStatement.setString(3, obj.getPwd());
      if (obj instanceof Medecin) {
        Medecin medecin = (Medecin) obj;
        preparedStatement.setString(4, medecin.getTypeCompte());
      } else {
        Secretaire secretaire = (Secretaire) obj;
        preparedStatement.setString(4, secretaire.getTypeCompte());
      }
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Closer.close(preparedStatement);
      Context.destroy();
    }
  }

  private Medecin getMedecin(ResultSet rs) throws SQLException {
    return new Medecin(
      rs.getInt("id_compte"),
      rs.getString("login"),
      rs.getString("password"),
      rs.getString("typeCompte")
    );
  }

  private Secretaire getSecretaire(ResultSet rs) throws SQLException {
    return new Secretaire(
      rs.getInt("id_compte"),
      rs.getString("login"),
      rs.getString("password"),
      rs.getString("typeCompte")
    );
  }

  @Override
  public Compte findByKey(Integer key) {
    Compte compte = null;
    PreparedStatement ps = null;
    try {
      ps =
        Context
          .getContext()
          .getConnection()
          .prepareStatement("select * from compte where id=?");
      ps.setInt(1, key);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        if (rs.getString("type").equals("M")) {
          compte = getMedecin(rs);
        } else {
          compte = getSecretaire(rs);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Closer.close(ps);
      Context.destroy();
    }
    return compte;
  }

  @Override
  public void update(Compte obj) {
    PreparedStatement preparedStatement = null;
    Connection connection = null;
    try {
      connection = Context.getContext().getConnection();
      preparedStatement =
        connection.prepareStatement(
          "update compte set login=?,password=?,typeCompte=? where idCompte=?"
        );
      preparedStatement.setString(1, obj.getLogin());
      preparedStatement.setString(2, obj.getPwd());
      if (obj instanceof Medecin) {
        Medecin medecin = (Medecin) obj;
        preparedStatement.setString(3, medecin.getTypeCompte());
      } else {
        Secretaire secretaire = (Secretaire) obj;
        preparedStatement.setString(3, secretaire.getTypeCompte());
      }
      preparedStatement.setInt(4, obj.getIdCompte());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Closer.close(preparedStatement);
      Context.destroy();
    }
  }

  @Override
  public void delete(Compte obj) {
    deleteByKey(obj.getIdCompte());
  }

  @Override
  public void deleteByKey(Integer key) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement =
        Context
          .getContext()
          .getConnection()
          .prepareStatement("delete from compte where id=?");
      preparedStatement.setInt(1, key);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Closer.close(preparedStatement);
      Context.destroy();
    }
  }

  @Override
  public List<Compte> findAll() {
    List<Compte> comptes = new ArrayList<>();
    Statement statement = null;
    try {
      statement = Context.getContext().getConnection().createStatement();
      ResultSet rs = statement.executeQuery("select * from compte");
      while (rs.next()) {
        comptes.add(
          new Compte(
            rs.getInt("id_compte"),
            rs.getString("login"),
            rs.getString("password")
          )
        );
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      Closer.close(statement);
      Context.destroy();
    }
    return comptes;
  }

  @Override
  public void affichFileAttente(List<Patient> FileAttente) {
    System.out.println("Fille Attente:");
    for (Patient patient : FileAttente) {
      // @formatter:off
      System.out.println("Id: " + patient.getIdPatient() 
                        + "Nom: " + patient.getNom() 
                        + "Prenom: " + patient.getPrenom());
      // @formatter:on
    }
  }

  @Override
  public void affichProchainPatient(List<Patient> FileAttente) {
    System.out.println("Prochain patient" + FileAttente.get(0));
  }
}
