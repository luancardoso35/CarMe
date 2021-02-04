package model;
import java.sql.*;

public class UsuarioDAO {

    public static final String url = "jdbc:postgresql://localhost:5432/carme";
    public static final String user = "postgres";
    public static final String password = "luan";

    private static final String USER_TABLE = "usuario";
    public static final String CAR_TABLE = "carro";
    public static final String CPF_CAR_COLUMN = "usuario_cpf";
    public static final String RENAVAM_COLUMN = "renavam";
    public static final String CPF_COLUMN = "cpf";
    public static final String IMAGE_COLUMN = "imagename";
    public static final String NAME_COLUMN = "nome";
    public static final String EMAIL_COLUMN = "email";
    public static final String PHONE_COLUMN = "telefone";
    public static final String PASSWORD_COLUMN = "senha";
    private static final String INSERT_USER = "INSERT INTO " + USER_TABLE +
            " VALUES (?, ?, ?, ?, ?, ?)";
    public static final String QUERY_USER_REGISTER = "SELECT * FROM " + USER_TABLE + " WHERE "
            + CPF_COLUMN + " LIKE ?";
    public static final String QUERY_USER_LOGIN = "SELECT * FROM " + USER_TABLE
            + " WHERE " + CPF_COLUMN + " LIKE ? AND " + PASSWORD_COLUMN + " LIKE ?";
    public static final String QUERY_USER_CAR = "SELECT " + CPF_COLUMN +", " + NAME_COLUMN
            + ", " + EMAIL_COLUMN + ", " + PHONE_COLUMN + ", " + USER_TABLE + "." + IMAGE_COLUMN +
            " FROM " + USER_TABLE + ", " + CAR_TABLE + " WHERE " +  CPF_COLUMN +
            "=" +  CPF_CAR_COLUMN + " AND " + RENAVAM_COLUMN + "=?";
    public static final String QUERY_USER = "SELECT * FROM " + USER_TABLE + " WHERE " +
            CPF_COLUMN + " LIKE ?";
    public static final String UPDATE_USER = "UPDATE " + USER_TABLE + " SET " +
            NAME_COLUMN + " = ?, " + EMAIL_COLUMN + " = ?, " + PHONE_COLUMN + " = ? WHERE " +
            CPF_COLUMN + " LIKE ?";
    public static final String DELETE_USER = "DELETE FROM " + USER_TABLE + " WHERE " + CPF_COLUMN
            + " LIKE ?";

    private static PreparedStatement insertUser;
    private static PreparedStatement queryUserRegister;
    private static PreparedStatement queryUserLogin;
    private static PreparedStatement queryUserByCar;
    private static PreparedStatement queryUser;
    private static PreparedStatement updateUser;
    private static PreparedStatement deleteUser;

    public static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);


            if (conn != null) {
                return conn;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean insert(String name, String cpf , String email, String telefone, String password,
    String imageName) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                queryUserRegister = conn.prepareStatement(QUERY_USER_REGISTER);
                queryUserRegister.setString(1, cpf);
                ResultSet results = queryUserRegister.executeQuery();

                if (!results.next()) {
                    insertUser = conn.prepareStatement(INSERT_USER);
                    insertUser.setString(1, cpf);
                    insertUser.setString(2, name);
                    insertUser.setString(3, email);
                    insertUser.setString(4, telefone);
                    insertUser.setString(5, password);
                    insertUser.setString(6, imageName);

                    int affectedRows = insertUser.executeUpdate();

                    conn.close();
                    return affectedRows == 1;
                } else {
                    conn.close();
                    return false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                } catch (SQLException exception) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public static Usuario queryUserLogin(String cpf, String password) {
        Connection conn = getConnection();

        if (conn != null) {
            try {
                queryUserLogin = conn.prepareStatement(QUERY_USER_LOGIN);
                queryUserLogin.setString(1, cpf);
                queryUserLogin.setString(2, password);
                ResultSet results = queryUserLogin.executeQuery();
                if (results.next()) {
                    conn.close();
                    return new Usuario(results.getString("nome"), results.getString("email"),
                            results.getString("telefone"), results.getString("imagename"),
                            results.getString("cpf"));

                } else {
                    conn.close();
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                } catch (SQLException exception) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return null;
    }

    public static Usuario queryUserCar(String renavam) {
        Connection conn = getConnection();

        if (conn != null) {
            try {
                queryUserByCar = conn.prepareStatement(QUERY_USER_CAR);
                queryUserByCar.setString(1, renavam);


                ResultSet results = queryUserByCar.executeQuery();
                if (results.next()) {
                    String telefone = editTelefone(results.getString("telefone"));

                    Usuario user = new Usuario(results.getString("nome"), results.getString("email"),
                           telefone, results.getString("imagename"), results.getString("cpf"));
                    conn.close();
                    return user;
                } else {
                    conn.close();
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                } catch (SQLException exception) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return null;
    }

    public static String editTelefone (String telefone) {
        StringBuilder tel = new StringBuilder("(");
        tel.append(telefone.charAt(0));
        tel.append(telefone.charAt(1));
        tel.append(") ");

        for(int i = 0; i < 4; i++) {
            tel.append(telefone.charAt(i+2));
        }

        if (telefone.length() == 11) {
            tel.append(telefone.charAt(6));
            tel.append("-");
            for (int i = 0; i < 4; i++) {
                tel.append(telefone.charAt(i+7));
            }
        } else {
            tel.append("-");
            for (int i = 0; i < 4; i++) {
                tel.append(telefone.charAt(i+6));
            }
        }

        return tel.toString();
    }

    public static Usuario queryUsuario(String cpf) {
        Connection conn = getConnection();

        if (conn != null) {
            try {
                queryUser = conn.prepareStatement(QUERY_USER);
                queryUser.setString(1, cpf);

                ResultSet results = queryUser.executeQuery();
                conn.close();
                if (results.next()) {
                    return new Usuario(results.getString("nome"), results.getString("email"),
                            results.getString("telefone"), results.getString("imagename"),
                            results.getString("cpf"));
                }
                return null;
            } catch (SQLException exception) {
                exception.printStackTrace();
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static boolean updateUser (String cpf, String nome, String email, String telefone) {
        Connection conn = getConnection();

        if (conn != null) {
            try {
                updateUser = conn.prepareStatement(UPDATE_USER);
                updateUser.setString(1, nome);
                updateUser.setString(2, email);
                updateUser.setString(3, telefone);
                updateUser.setString(4, cpf);

                int affectedRows = updateUser.executeUpdate();
                conn.close();
                return affectedRows == 1;
            } catch (SQLException exception) {
                exception.printStackTrace();
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean deleteUser (String cpf) {
        Connection conn = getConnection();

        if (conn != null) {
            try {
                deleteUser = conn.prepareStatement(DELETE_USER);

                deleteUser.setString(1, cpf);

                int affectedRows = deleteUser.executeUpdate();
                conn.close();
                return affectedRows == 1;
            } catch (SQLException exception) {
                exception.printStackTrace();
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}


