package model;

import java.sql.*;
import java.util.HashMap;

public class CarroDAO {
    private static final String CAR_TABLE = "carro";
    private static final String COLUMN_MARCA = "marca";
    public static final String COLUMN_CPF = "usuario_cpf";
    private static final String COLUMN_MODELO = "modelo";
    private static final String COLUMN_CAMBIO = "cambio";
    private static final String COLUMN_ASSENTO = "assentos";
    private static final String COLUMN_ANO = "ano";
    private static final String COLUMN_RENAVAM = "renavam";

    public static final String INSERT_CAR = "INSERT INTO " + CAR_TABLE + " VALUES (?, ?, ?, ?," +
            " ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String QUERY_CARS_GET_METHOD = "SELECT * FROM " + CAR_TABLE;
    public static final String QUERY_CPF_NOT_LIKE = " WHERE " + COLUMN_CPF + " NOT LIKE ?";
    public static final String QUERY_CPF_LIKE = COLUMN_CPF + " LIKE ?";
    private static String QUERY_CARS = "SELECT * FROM " + CAR_TABLE + " WHERE ";
    public static final String QUERY_RENAVAM = COLUMN_RENAVAM + " LIKE ?";
    private static final String QUERY_MARCA = COLUMN_MARCA + " ILIKE ?";
    private static final String QUERY_MODELO = COLUMN_MODELO + " ILIKE ?";
    private static final String QUERY_CAMBIO = COLUMN_CAMBIO + " ILIKE ?";
    private static final String QUERY_ASSENTO = COLUMN_ASSENTO + " = ?";
    private static final String QUERY_ANO = COLUMN_ANO + " LIKE ?";
    public static final String DELETE_USER_CARS = "DELETE FROM " + CAR_TABLE + " WHERE " + COLUMN_CPF
            + " LIKE ?";
    public static final String DELETE_CAR = "DELETE FROM " + CAR_TABLE + " WHERE " + COLUMN_RENAVAM
            + " LIKE ?";

    private static PreparedStatement queryCarsFilters;
    private static PreparedStatement queryCarByRenavam;
    private static PreparedStatement insertCar;
    private static PreparedStatement queryCarsByCPF;
    private static PreparedStatement deleteUserCars;
    private static PreparedStatement deleteCar;

    private static HashMap<String, Carro> carMap = new HashMap<>();

    public static HashMap<String, Carro> queryCars(String marca, String modelo, String cambio,
                                                   int assento, String ano, String cpf) {
        Connection conn = UsuarioDAO.getConnection();
        if (conn != null) {
            carMap.clear();

            String[] filtros = new String[4];
            boolean cpfUser = false;
            boolean filtroAssento = false;
            int filtrosCount = 0;
            StringBuilder query = new StringBuilder(QUERY_CARS_GET_METHOD);

            if (cpf != null) {
                query.append(QUERY_CPF_NOT_LIKE);
                cpfUser = true;
            } else {
                if (!marca.equals("") || assento != 0 || !ano.equals("") || !cambio.equals("") || !modelo.equals("")) {
                    query.append(" WHERE ");
                }
            }

            if (!marca.equals("")) {
                if (cpfUser) {
                    query.append(" AND ");
                }
                query.append(QUERY_MARCA);
                filtros[filtrosCount] = "%" + marca + "%";
                filtrosCount++;
            }
            if (!modelo.equals("")) {
                if (cpfUser || filtrosCount != 0) {
                    query.append(" AND ");
                }
                query.append(QUERY_MODELO);
                filtros[filtrosCount] = "%" + modelo + "%";
                filtrosCount++;
            }
            if (!cambio.equals("")) {
                if (cpfUser || filtrosCount != 0) {
                    query.append(" AND ");
                }
                query.append(QUERY_CAMBIO);
                filtros[filtrosCount] = cambio;
                filtrosCount++;
            }

            if (!ano.equals("")) {
                if (cpfUser || filtrosCount != 0) {
                    query.append(" AND ");
                }
                query.append(QUERY_ANO);
                filtros[filtrosCount] = ano;
                filtrosCount++;
            }

            if (assento != 0) {
                if (cpfUser || filtrosCount != 0) {
                    query.append(" AND ");
                }
                query.append(QUERY_ASSENTO);
                filtroAssento = true;
            }


            String queryCars = query.toString();

            try {
                queryCarsFilters = conn.prepareStatement(queryCars);
                if (cpfUser) {
                    queryCarsFilters.setString(1, cpf);

                    if (filtros[0] != null) {
                        for (int i = 1; i <= filtrosCount; i++) {
                            queryCarsFilters.setString(i + 1, filtros[i - 1]);
                        }
                    }

                    if (filtroAssento) {
                        queryCarsFilters.setInt(filtrosCount + 2, assento);
                    }
                } else if (filtros[0] != null) {
                    for (int i = 1; i <= filtrosCount; i++) {
                        queryCarsFilters.setString(i, filtros[i - 1]);
                    }

                    if (filtroAssento) {
                        queryCarsFilters.setInt(filtrosCount + 1, assento);
                    }
                }

                ResultSet results = queryCarsFilters.executeQuery();

                while (true) {
                    if (results.next()) {
                        Carro carro = new Carro(results.getString("marca"), results.getString("modelo"),
                                results.getString("ano"), results.getString("cor"), results.getString("cambio"),
                                results.getInt("assentos"), results.getString("quilometragem"),
                                results.getString("combustivel"), results.getString("imagename"),
                                results.getString("preco"), results.getString("cidade"));

                        carMap.put(results.getString("renavam"), carro);
                    } else {
                        break;
                    }
                }
                        conn.close();
                    if (carMap.size() != 0) {
                        return carMap;
                    }
                    return null;

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                } catch (SQLException exception) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static boolean insertCar(String marca, String modelo, String cambio, int assentos, String ano,
                                    String renavam, String cor, String quilometragem, String combustivel,
                                    String preco, String cpfUsuario, String imageName, String cidade) {
        Connection conn = UsuarioDAO.getConnection();


        if (conn != null) {
            String queryCar = QUERY_CARS + QUERY_RENAVAM;

            try {
                queryCarByRenavam = conn.prepareStatement(queryCar);
                queryCarByRenavam.setString(1, renavam);

                ResultSet results = queryCarByRenavam.executeQuery();

                if (!results.next()) {

                    insertCar = conn.prepareStatement(INSERT_CAR);

                    insertCar.setString(1, renavam);
                    insertCar.setString(2, marca);
                    insertCar.setString(3, modelo);
                    insertCar.setString(4, cor);
                    insertCar.setString(5, ano);
                    insertCar.setString(6, quilometragem);
                    insertCar.setString(7, combustivel);
                    insertCar.setString(8, cambio);
                    insertCar.setInt(9, assentos);
                    insertCar.setString(10, preco);
                    insertCar.setString(11, cpfUsuario);
                    insertCar.setString(12, imageName);
                    insertCar.setString(13, cidade);

                    int affectedRows = insertCar.executeUpdate();
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

    public static Carro queryCarByRenavam(String renavam) {
        Connection conn = UsuarioDAO.getConnection();

        if (conn != null) {
            String queryCar = QUERY_CARS + QUERY_RENAVAM;

            try {
                queryCarByRenavam = conn.prepareStatement(queryCar);
                queryCarByRenavam.setString(1, renavam);

                ResultSet results = queryCarByRenavam.executeQuery();
                conn.close();
                if(results.next()) {
                    return new Carro(results.getString("marca"), results.getString("modelo"),
                            results.getString("ano"), results.getString("cor"), results.getString("cambio"),
                            results.getInt("assentos"), results.getString("quilometragem"),
                            results.getString("combustivel"), results.getString("imageName"),
                            results.getString("preco"), results.getString("cidade"));
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

    public static HashMap<String, Carro> queryCarsByUser(String cpf) {
        carMap.clear();
        Connection conn = UsuarioDAO.getConnection();

        if (conn != null) {
            String queryCars = QUERY_CARS + QUERY_CPF_LIKE;
            try {
                queryCarsByCPF = conn.prepareStatement(queryCars);
                queryCarsByCPF.setString(1, cpf);

                ResultSet results = queryCarsByCPF.executeQuery();

                while(true) {
                    if (results.next()) {
                        Carro carro = new Carro(results.getString("marca"), results.getString("modelo"),
                                results.getString("ano"), results.getString("cor"), results.getString("cambio"),
                                results.getInt("assentos"), results.getString("quilometragem"),
                                results.getString("combustivel"), results.getString("imagename"),
                                results.getString("preco"), results.getString("cidade"));

                        carMap.put(results.getString("renavam"), carro);
                    } else {
                        break;
                    }
                }
                conn.close();
                if (carMap.size() != 0) {
                    return carMap;
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

    public static boolean deleteUserCars (String cpf) {
        Connection conn = UsuarioDAO.getConnection();

        if (conn != null) {
            try {
                deleteUserCars = conn.prepareStatement(DELETE_USER_CARS);
                deleteUserCars.setString(1, cpf);

                deleteUserCars.executeUpdate();
                conn.close();
                return true;
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

    public static boolean deleteCar(String renavam) {
        Connection conn = UsuarioDAO.getConnection();

        if (conn != null) {
            try {
                deleteCar = conn.prepareStatement(DELETE_CAR);
                deleteCar.setString(1, renavam);

                int affectedRows = deleteCar.executeUpdate();
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