package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.util.StringUtils;

import bdutils.ConnectionBD;
import exceptions.DaoExceptions;
import model.Fita;

public class FitasDao {

	private static final String TABLE = "fitas";
	
	private enum COLUNAS{
		id, nome, ano_lancamento, sinopse, duracao;
	};
	
	public Fita insert(Fita f) throws DaoExceptions{
		validate(f, false);
		
		StringBuilder sb = new StringBuilder("INSERT INTO "+TABLE);
		sb.append(" ( "+COLUNAS.nome.name());
		sb.append(" , "+COLUNAS.ano_lancamento.name());
		sb.append(" , "+COLUNAS.sinopse.name());
		sb.append(" , "+COLUNAS.duracao.name());
		sb.append(" )");
		sb.append(" VALUES");
		sb.append(" ( ?,?,?,? )");
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setString(1, f.getNome());
			ps.setInt(2, f.getAnoLancamento());
			ps.setString(3, f.getSinopse());
			ps.setString(4, f.getDuracao());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				f.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return f;
	}
	
	public Fita get(Integer id) throws DaoExceptions{
		if(id==null)
			id=0;
		
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(COLUNAS.id.name());
		sb.append(",");
		sb.append(COLUNAS.nome.name());
		sb.append(",");
		sb.append(COLUNAS.ano_lancamento.name());
		sb.append(",");
		sb.append(COLUNAS.sinopse.name());
		sb.append(",");
		sb.append(COLUNAS.duracao.name());
		sb.append(" FROM "+TABLE);
		sb.append(" WHERE ");
		sb.append(COLUNAS.id.name()+" = ?");
		
		Fita f = null;
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				f = new Fita();
				f.setId(rs.getInt(COLUNAS.id.name()));
				f.setNome(rs.getString(COLUNAS.nome.name()));
				f.setAnoLancamento(rs.getInt(COLUNAS.ano_lancamento.name()));
				f.setDuracao(rs.getString(COLUNAS.duracao.name()));
				f.setSinopse(rs.getString(COLUNAS.sinopse.name()));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoExceptions("Erro ao executar comando no BD");
		} finally {
			ConnectionBD.close();
		}
		
		return f;
	}
	
	public boolean update(Fita f) throws DaoExceptions{
		validate(f, true);
		
		boolean ret = false;
		
		StringBuilder sb = new StringBuilder("UPDATE "+TABLE);
		sb.append(" SET "+COLUNAS.nome+" = ?");
		sb.append(", ");
		sb.append(COLUNAS.ano_lancamento+" = ?");
		sb.append(", ");
		sb.append(COLUNAS.sinopse+" = ?");
		sb.append(", ");
		sb.append(COLUNAS.duracao+" = ?");
		sb.append(" WHERE ");
		sb.append(COLUNAS.id.name()+" = ?");
		
		try {
			PreparedStatement ps = new ConnectionBD().getPrepareStatement(sb.toString());
			
			ps.setString(1, f.getNome());
			ps.setInt(2, f.getAnoLancamento());
			ps.setString(3, f.getSinopse());
			ps.setString(4, f.getDuracao());
			
			ps.setInt(5, f.getId());
			
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
	
	private void validate(Fita f, boolean update) throws DaoExceptions{
		if(f == null) {
			throw new DaoExceptions("Informe a fita");
		}
		else if(StringUtils.isNullOrEmpty(f.getNome())) {
			throw new DaoExceptions("O nome é obrigatório");
		}
		else if(f.getNome().length()<3) {
			throw new DaoExceptions("O nome deve ter no mínimo 3 caracteres");
		}
		else if(update && f.getId()==null) {
			throw new DaoExceptions("Informe a fita com um ID");
		}
		else if(f.getAnoLancamento()<0) {
			throw new DaoExceptions("Informe o ano");
		}
		else if(StringUtils.isNullOrEmpty(f.getDuracao()) || f.getDuracao().length()<5) {
			throw new DaoExceptions("Informe a duração");
		}
	}
}