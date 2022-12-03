package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bdutils.ConnectionBD;
import exceptions.DaoExceptions;
import model.Fita;
import model.FitaGenero;
import model.Genero;

public class FitaGenerosDao {

	private static final String TABLE = "fitas_generos";
	
	private enum COLUNAS{
		cod_fita,
		cod_genero;
	};
	
	public FitaGenero insert(FitaGenero fg) throws DaoExceptions{
		validate(fg);
		
		StringBuilder sb = new StringBuilder("INSERT INTO "+TABLE);
		sb.append(" ( "+COLUNAS.cod_fita.name());
		sb.append(" , "+COLUNAS.cod_genero.name());
		sb.append(" ) ");
		sb.append(" VALUES");
		sb.append(" ( ?, ? )");
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setInt(1, fg.getCodFita().getId());
			ps.setInt(2, fg.getCodGenero().getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return fg;
	}
	
	public FitaGenero get(Integer codFita, Integer codGenero) throws DaoExceptions{
		if(codGenero==null)
			codGenero=0;
		
		if(codFita==null)
			codFita=0;
		
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(COLUNAS.cod_fita.name());
		sb.append(",");
		sb.append(COLUNAS.cod_genero.name());
		sb.append(" FROM "+TABLE);
		sb.append(" WHERE ");
		sb.append(COLUNAS.cod_fita.name()+" = ? ");
		sb.append(" AND "+COLUNAS.cod_genero.name()+" = ? ");
		
		FitaGenero fg = null;
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setInt(1, codFita);
			ps.setInt(2, codGenero);
			ps.setInt(3, codFita);
			ps.setInt(4, codGenero);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				fg = new FitaGenero();
				fg.setCodFita(new Fita(rs.getInt(COLUNAS.cod_fita.name())));
				fg.setCodGenero(new Genero(rs.getInt(COLUNAS.cod_genero.name())));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return fg;
	}
	
	public boolean delete(Integer codFita, Integer codGenero) throws DaoExceptions{
		if(codGenero==null)
			codGenero=0;
		
		if(codFita==null)
			codFita=0;
		
		boolean ret = false;
		
		StringBuilder sb = new StringBuilder("DELETE FROM "+TABLE);
		sb.append(" WHERE ");
		sb.append(COLUNAS.cod_fita.name()+" = ?");
		sb.append(" AND "+COLUNAS.cod_genero.name()+" = ?");
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setInt(1, codFita);
			ps.setInt(2, codGenero);
			
			ret = ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return ret;
	}
	
	private void validate(FitaGenero fg) throws DaoExceptions{
		if(fg == null || fg.getCodFita()==null || fg.getCodGenero()==null) {
			throw new DaoExceptions("Informe a fita e o gênero");
		}
		else {
			FitaDao fitaDao = new FitaDao();
			if(fitaDao.get(fg.getCodFita().getId())==null) {
				throw new DaoExceptions("Informe a fita");
			}
			
			GeneroDao generoDao = new GeneroDao();
			if(generoDao.get(fg.getCodGenero().getId())==null) {
				throw new DaoExceptions("Informe a fita e o gênero");
			}
		}
	}
}