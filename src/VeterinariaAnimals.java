package src;

import src.Modelo.Animal;
import src.veterinaria.ConexaoBancodeDados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VeterinariaAnimals extends ConexaoBancodeDados {
    Connection connection;

    public VeterinariaAnimals() throws SQLException {
        this.connection = ConexaoBancodeDados.getInstance().getConnection();
    }

    public void Salvar(Animal animal) {
        String nome = animal.getNome();
        String raca = animal.getRaca();
        // Salva o aluno no banco de dados
        String query = "INSERT INTO animal ( nome, raca) VALUES ( '"+ nome + "', '" + raca + "');";
        try {
            this.connection.createStatement().executeUpdate(query);
            System.out.println("Animal salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> buscarAnimal() {
        String consultaTodos = "SELECT * FROM animals ORDER BY nome ASC";
        return executaConsulta(consultaTodos);
    }

    public ArrayList<String> buscarAnimals(int codigo) {
        String consultaPorCodigo = "SELECT * FROM animals WHERE codigo = " + codigo + " ORDER BY nome ASC";
        return executaConsulta(consultaPorCodigo);
    }

    public ArrayList<String> buscarAnimals(String nome) {
        String consultaPorNome = "SELECT * FROM animals WHERE nome LIKE '%" + nome + "%' ORDER BY nome ASC";
        return executaConsulta(consultaPorNome);
    }

    public ArrayList<String> executaConsulta(String consulta) {
        ArrayList<String> resultado = new ArrayList<>();
        try {
            ResultSet linhas = this.connection.createStatement().executeQuery(consulta);

            while (linhas.next()) {
                int codigo = linhas.getInt("codigo");
                String raca = linhas.getString("nome");
                String linha = codigo + " - " + raca;
                resultado.add(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public void deletarAnimals(int codigo) {
        String query = "DELETE FROM animals WHERE codigo = " + codigo;
        executaDelete(query);
    }

    public void deletarAnimals(String nome) {
        String query = "DELETE FROM animals WHERE nome LIKE '%" + nome + "%'";
        executaDelete(query);
    }

    public void executaDelete(String consultaDeDelete) {
        try {
            this.connection.createStatement().executeUpdate(consultaDeDelete);
            System.out.println("Animal deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarAnimals(int codigo, String novoNome) {
        String query = "UPDATE animals SET nome = '" + novoNome + "' WHERE codigo = " + codigo;
        try {
            System.out.println("Animal editado com sucesso! " + query);
            this.connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
