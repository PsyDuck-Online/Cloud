package cloud;

import common.Producto;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Servidor {

    public static void main(String[] args) {
        int port = 9090;
        ServerSocket serverSocket;

        File f = new File("");
        String ruta = f.getAbsolutePath();
        String carpeta = "archivos_servidor";
        String rutaCarpeta = ruta + "/" + carpeta + "/";
        String rutaInventario = rutaCarpeta + "inventario.txt";

        File f2 = new File(rutaCarpeta);
        f2.mkdirs();
        f2.setWritable(true);

        File docInventario = new File(rutaInventario);

        ArrayList<Producto> inventario = new ArrayList<>();
        initInventario(inventario, rutaInventario, rutaCarpeta);
        System.out.println("Productos en el inventario: " + inventario.size());

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            System.out.println("Servidor Iniciado !!!");

            while (true) {
                System.out.println("Esperando Conexion...");
                try {
                    Socket cl = serverSocket.accept();
                    System.out.println("Conexion Establecida = {" + cl.getInetAddress() + ":" + cl.getPort() + "}");
                    DataOutputStream dosCl = new DataOutputStream(cl.getOutputStream());
                    DataInputStream disCl = new DataInputStream(cl.getInputStream());
                    ObjectOutputStream oosCl = new ObjectOutputStream(cl.getOutputStream());

                    enviarImagenes(dosCl, disCl, inventario);
                    enviarInventario(oosCl, inventario);

                    boolean compra = false;
                    while (true) {
                        int op = disCl.readInt();
                        if (op == 1) {

                            // Agregar aAl Carrito //
                            int id = disCl.readInt();
                            int validacion = 0;
                            for (Producto p : inventario) {
                                if (p.getId() == id) {
                                    if (p.getCantidad() > 0) {
                                        validacion = 1;
                                        p.disminuir(1);
                                        compra = false;
                                    } else {
                                        validacion = 0;
                                    }
                                    // Enviamos La Validacion //
                                    dosCl.writeInt(validacion);
                                    dosCl.flush();
                                    break;
                                }
                            }

                        } else if (op == 2) {

                            // Quitar Del Carrito //
                            int id = disCl.readInt();
                            int cantidad = disCl.readInt();
                            boolean validacion = false;
                            for (Producto p : inventario) {
                                if (p.getId() == id) {
                                    p.agregar(cantidad);
                                    validacion = true;
                                    compra = false;
                                    break;
                                }
                            }
                            dosCl.writeBoolean(validacion);
                            dosCl.flush();

                        } else if (op == 3) {

                            // Modificar Cantidad //
                            int id = disCl.readInt();
                            int diferencia = disCl.readInt();
                            boolean validacion = false;
                            for (Producto p : inventario) {
                                if (p.getId() == id) {
                                    if ((p.getCantidad() + diferencia) >= 0) {
                                        p.agregar(diferencia);
                                        validacion = true;
                                        compra = false;
                                    }
                                    break;
                                }
                            }
                            dosCl.writeBoolean(validacion);
                            dosCl.flush();

                        } else if (op == 4) {

                            // Comprar //
                            boolean validacion = false;
                            try {
                                PrintWriter pw = new PrintWriter(docInventario);

                                String line;
                                for (Producto p : inventario) {
                                    line = String.format("%s/%.2f/%d/%s",
                                            p.getNombre(),
                                            p.getPrecio(),
                                            p.getCantidad(),
                                            p.getImg().getName()
                                    );
                                    pw.println(line);
                                }
                                validacion = true;
                                pw.close();
                            } catch (FileNotFoundException e) {
                                System.err.println(e.getMessage());
                            }
                            dosCl.writeBoolean(validacion);
                            dosCl.flush();

                        } else if (op == 5) {

                            // Salir //
                            break;

                        }
                    }

                    // Cerrando Conexion //
                    dosCl.close();
                    disCl.close();
                    cl.close();

                } catch (IOException e) {
                    initInventario(inventario, rutaInventario, rutaCarpeta);
                    System.err.println(e.getMessage());
                }

                System.out.println("Conexion Cerrada!!! \n\n");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    private static void initInventario(ArrayList<Producto> inventario, String rutaInventario, String rutaCarpeta) {
        try {
            inventario.clear();
            File doc = new File(rutaInventario);
            if (doc.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(doc));

                String linea = "";

                while ((linea = br.readLine()) != null) {
                    String[] arrayProducto = linea.split("/");
                    String nombre = arrayProducto[0];
                    float precio = Float.parseFloat(arrayProducto[1]);
                    int cantidad = Integer.parseInt(arrayProducto[2]);
                    File img = new File(rutaCarpeta + arrayProducto[3]);

                    Producto p = new Producto(nombre, precio, cantidad, img);
                    inventario.add(p);
                }
                br.close();
            } else {
                System.err.println("El Archivo No Existe !!!");
                System.exit(0);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    private static void enviarImagenes(DataOutputStream dosCl, DataInputStream disCl, ArrayList<Producto> inventario) {
        try {
            dosCl.writeInt(inventario.size());
            dosCl.flush();

            for (Producto p : inventario) {
                File img = p.getImg();
                sendFile(img, dosCl);
                disCl.readInt();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    private static void enviarInventario(ObjectOutputStream oosCl, ArrayList<Producto> inventario) {
        try {
            oosCl.flush();
            oosCl.writeObject(inventario);
            oosCl.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void sendFile(File img, DataOutputStream dosCl) {
        try {
            DataInputStream disFile = new DataInputStream(new FileInputStream(img));

            String name = img.getName();
            long tam = img.length();

            dosCl.writeUTF(name);
            dosCl.flush();
            dosCl.writeLong(tam);
            dosCl.flush();

            long enviado = 0;
            int l = 0;

            while (enviado < tam) {
                byte[] b = new byte[1024];
                l = disFile.read(b);
                dosCl.write(b, 0, l);
                dosCl.flush();
                enviado += l;
            }
            disFile.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}
