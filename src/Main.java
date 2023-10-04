package src;

import src.Modelo.Animal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        try {
            menuInicial();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void menuInicial() throws SQLException {
        out("Para cadastrar animal digite 1 \nPara cadastrar dono digite 2\nPara buscar animal digite 3");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                out("Cadastrando animal");
                cadastrarAnimals();
            }
//           case 2:
//               out("Cadastrando dono");
//               cadastrarDono();
//                break;
            case 3 -> {
                out("Buscando animal");
                buscarAnimal();
            }
            default -> out("Opção inválida");
        }
    }

    private static void buscarAnimal() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        out("Para listar todos animais digite 1 \nPara buscar pelo nome digite 2");
        VeterinariaAnimals veterinariaAnimals = new VeterinariaAnimals();
        int opcao = scanner.nextInt();
        ArrayList<String> animals = null;
        switch (opcao) {
            case 1 -> {
                out("Listando todos os animais");
                animals = veterinariaAnimals.buscarAnimal();
            }
            case 2 -> {
                scanner.reset();
                out("Digite o nome do animal:");
                String animal = scanner.nextLine();
                animals = veterinariaAnimals.buscarAnimal();
            }
            default -> out("Opção inválida");
        }

        for (String animal : animals) {
            out(animal);
        }

        scanner.reset();
        out("Para editar animal dige e\nPara deletar animal digite d");

        String opcaoEditar = scanner.next();

        switch (opcaoEditar) {
            case "e":
                out("Digite o código do animal que deseja editar:");
                int codigo = scanner.nextInt();
                scanner.reset();
                out("Digite o novo nome do animal:");
                String novoNome = scanner.next();
                veterinariaAnimals.editarAnimals(codigo, novoNome);
                break;
            case "d":
                out("Digite o codigo do que aluno que deseja deletar");
                int codigoDelete = scanner.nextInt();
                veterinariaAnimals.deletarAnimals(codigoDelete);
            default:
                out("Opção inválida");
                break;
        }


    }

//    private static void cadastrarProfessor() throws SQLException {
//
//        Professor professor = new Professor();
//        PersistenciaProfessor persistenciaProfessor = new PersistenciaProfessor();
//        PersistenciaMateria persistenciaMateria = new PersistenciaMateria();
//        ArrayList<String> materias = null;
//        try {
//            materias = persistenciaMateria.buscarMaterias();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        Scanner scanner = new Scanner(System.in);
//
//        out("Digite o nome do professor:");
//        String nome = scanner.nextLine();
//        professor.setNome(nome);
//        for (String materia : materias) {
//            out(materia);
//        }
//        out("Digite o código da matéria do professor:");
//        professor.setMateria(scanner.nextInt());
//        out("Verifique os dados do professor:");
//        out("Nome: " + professor.getNome());
//        out("Matéria: " + professor.getMateria());
//        out("Para confirmar digite S, para corrigir digite N, para voltar ao menu inicial digite V");
//        persistenciaProfessor.Salvar(professor);
//}


    public static void cadastrarAnimals() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        VeterinariaAnimals veterinariaAnimals = new VeterinariaAnimals();

        Animal animals = new Animal();
        // Solicita ao usuário que digite uma entrada
        out("Digite o nome do animal:");
        animals.setNome(scanner.nextLine());
        out("Verifique os dados do animal:");
        out("Nome: " + animals.getNome());
        out("Raca: " + animals.getNome());
        out("Para confirmar digite S, para corrigir digite N, para voltar ao menu inicial digite V");
        String confirmacao = scanner.nextLine();
        switch (confirmacao) {
            case "S" -> veterinariaAnimals.Salvar(animals);
            case "N" -> {
                out("Cadastro cancelado");
                cadastrarAnimals();
            }
            default -> {
                out("Opção inválida");
                menuInicial();
            }
        }
        menuInicial();
    }

    private static void out(String s) {
        System.out.println(s);
    }
}