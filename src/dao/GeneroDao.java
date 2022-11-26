package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.util.StringUtils;

import bdutils.ConnectionBD;
import exceptions.DaoExceptions;
import model.Genero;

public class GeneroDao {

	private static final String TABLE = "generos";
	
	private enum COLUNAS{
		id, nome;
	};
	
	public Genero insert(Genero g) throws DaoExceptions{
		validate(g, false);
		
		StringBuilder sb = new StringBuilder("INSERT INTO "+TABLE);
		sb.append(" ( "+COLUNAS.nome.name()+" )");
		sb.append(" VALUES");
		sb.append(" ( ? )");
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setString(1, g.getNome());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				g.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return g;
	}
	
	public Genero get(Integer id) throws DaoExceptions{
		if(id==null)
			id=0;
		
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(COLUNAS.id.name());
		sb.append(",");
		sb.append(COLUNAS.nome.name());
		sb.append(" FROM "+TABLE);
		sb.append(" WHERE ");
		sb.append(COLUNAS.id.name()+" = ?");
		
		Genero g = null;
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				g = new Genero();
				g.setId(rs.getInt(COLUNAS.id.name()));
				g.setNome(rs.getString(COLUNAS.nome.name()));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return g;
	}
	
	public boolean update(Genero g) throws DaoExceptions{
		validate(g, true);
		
		boolean ret = false;
		
		StringBuilder sb = new StringBuilder("UPDATE "+TABLE);
		sb.append(" SET "+COLUNAS.nome+" = ?");
		sb.append(" WHERE ");
		sb.append(COLUNAS.id.name()+" = ?");
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setString(1, g.getNome());
			ps.setInt(2, g.getId());
			
			ret = ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return ret;
	}
	
	public boolean delete(Integer id) throws DaoExceptions{
		if(id==null) {
			id = 0;
		}
		
		boolean ret = false;
		
		StringBuilder sb = new StringBuilder("DELETE FROM "+TABLE);
		sb.append(" WHERE ");
		sb.append(COLUNAS.id.name()+" = ?");
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setInt(1, id);
			
			ret = ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return ret;
	}
	
	private void validate(Genero g, boolean update) throws DaoExceptions{
		if(g == null) {
			throw new DaoExceptions("Informe o gênero");
		}
		else if(StringUtils.isNullOrEmpty(g.getNome())) {
			throw new DaoExceptions("O nome é obrigtório");
		}
		else if(g.getNome().length()<3) {
			throw new DaoExceptions("O nome deve ter no mínimo 3 caracteres");
		}
		else if(update && g.getId()==null) {
			throw new DaoExceptions("Informe o gênero com um ID");
		}
	}
}