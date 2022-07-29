package cloud;

import common.Producto;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public class VentanaCliente extends javax.swing.JFrame {

    /**
     * Creates new form VentanaCliente
     */
    // ============= //
    // Mis variables //
    // ============= //
    private String host = "localhost";
    private int port = 9090;
    private Socket socket;

    private DataOutputStream dos;
    private DataInputStream dis;
    private ObjectInputStream ois;
    private String rutaCarpeta;

    private ArrayList<Producto> carrito = new ArrayList<>();
    private ArrayList<Producto> tienda = new ArrayList<>();

    private DefaultTableModel modeloCarrito;
    private DefaultTableModel modeloTienda;

    public VentanaCliente() {
        initComponents();
        initCliente();
        initHeaders();
        llenarTablas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JScrollPane1 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTienda = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaCarrito.setFocusable(false);
        tablaCarrito.setGridColor(new java.awt.Color(0, 0, 0));
        tablaCarrito.setShowGrid(true);
        JScrollPane1.setViewportView(tablaCarrito);

        tablaTienda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaTienda.setGridColor(new java.awt.Color(0, 0, 0));
        tablaTienda.setShowGrid(true);
        jScrollPane2.setViewportView(tablaTienda);

        jLabel1.setText("CARRITO:");

        jLabel2.setText("Tienda:");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(JScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(btnComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel2)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarProducto();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
        quitarProducto();
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        modificarProducto();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        // TODO add your handling code here:
        comprar();
    }//GEN-LAST:event_btnComprarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        salir();
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaCarrito;
    private javax.swing.JTable tablaTienda;
    // End of variables declaration//GEN-END:variables

    private void initCliente() {
        try {
            socket = new Socket(host, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            File f = new File("");
            String ruta = f.getAbsolutePath();
            String carpeta = "archivos_cliente";
            rutaCarpeta = ruta + "/" + carpeta + "/";
            File f2 = new File(rutaCarpeta);
            f2.mkdirs();
            f2.setWritable(true);

            recibirImagenes();
            recibirInventario();

        } catch (Exception e) {
        }
    }

    private void initHeaders() {
        modeloCarrito = new DefaultTableModel();
        modeloTienda = new DefaultTableModel();
        modeloCarrito.setColumnIdentifiers(new Object[]{
            "Id",
            "Nombre",
            "Precio",
            "Cantidad",
            "Imagen"
        });
        modeloTienda.setColumnIdentifiers(new Object[]{
            "Id",
            "Nombre",
            "Precio",
            "Cantidad",
            "Imagen"
        });

        tablaCarrito.setModel(modeloCarrito);
        tablaTienda.setModel(modeloTienda);
    }

    private void llenarTablas() {
        // Borramos el contenido de las tablas
        modeloTienda.setRowCount(0);
        modeloCarrito.setRowCount(0);
        // Llenamos la tabla carrito 
        for (Producto p : carrito) {
            Object object[] = new Object[]{
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getCantidad(),
                p.getImg()
            };
            modeloCarrito.addRow(object);
        }
        // Llenamos la tabla tienda 
        for (Producto p : tienda) {
            Object object[] = new Object[]{
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getCantidad(),
                p.getImg()
            };
            modeloTienda.addRow(object);
        }
    }

    private void agregarProducto() {
        try {
            // Obtenemos la fila seleccionada
            int fila = tablaTienda.getSelectedRow();
            // Comprobamos que exista
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar una fila primero");
            } else {
                // Obtenemos el id del producto
                int id = (int) tablaTienda.getValueAt(fila, 0);
                boolean enCarrito = false;
                // Enviamos la opcion '1 (Agregar)' al servidor
                dos.writeInt(1);
                dos.flush();

                // Enviamos el id del objeto
                dos.writeInt(id);
                dos.flush();

                // Comprobamos la validacion del servidor
                int valido = dis.readInt();
                if (valido == 1) {
                    // buscamos el producto dentro del carrito
                    for (Producto p : carrito) {
                        if (p.getId() == id) {
                            enCarrito = true;
                            break;
                        }
                    }
                    // Comprobamos si esta en el carrito
                    if (enCarrito) {
                        // Sumamos la cantidad al carrito
                        for (Producto p : carrito) {
                            if (p.getId() == id) {
                                p.agregar(1);
                            }
                        }
                    } else {
                        // Agregamos el producto al carrito
                        for (Producto p : tienda) {
                            if (p.getId() == id) {
                                carrito.add(new Producto(p));
                            }
                        }
                    }

                    // Restamos la cantidad a la tienda mostrada
                    for (Producto p : tienda) {
                        if (p.getId() == id) {
                            p.disminuir(1);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "SERVIDOR: Error al agregar producto");
                }
            }
            llenarTablas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void quitarProducto() {
        int fila = tablaCarrito.getSelectedRow();
        if (fila != -1) {
            try {
                // Obtenemos el id y la cantidad del producto
                int id = (int) modeloCarrito.getValueAt(fila, 0);
                int cantidad = (int) modeloCarrito.getValueAt(fila, 3);
                // Enviamos la opcion '2 (quitar)' al servidor
                dos.writeInt(2);
                dos.flush();
                // Enviamos el id y la cantidad
                dos.writeInt(id);
                dos.flush();
                dos.writeInt(cantidad);
                dos.flush();
                // Recibimos la validacion del servidor
                boolean validacion = dis.readBoolean();
                if (validacion) {
                    ArrayList<Producto> carritoAux = new ArrayList<>();
                    carritoAux.addAll(carrito);
                    for (Producto p : carritoAux) {
                        if (p.getId() == id) {
                            carrito.remove(p);
                        }
                    }
                    carritoAux.clear();
                    for (Producto p : tienda) {
                        if (p.getId() == id) {
                            p.agregar(cantidad);
                        }
                    }
                    llenarTablas();
                } else {
                    JOptionPane.showMessageDialog(null, "SERVIDOR: Error al quitar el producto");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Deves seleccionar un producto");
        }
    }

    private void modificarProducto() {
        int fila = tablaCarrito.getSelectedRow();
        if (fila != -1) {
            try {
                // Obtenemos el id del producto
                int id = (int) modeloCarrito.getValueAt(fila, 0);
                boolean validacion = false;
                // Obtenemos el producto
                for (Producto p : carrito) {
                    if (p.getId() == id) {
                        // Obtenemos la cantidad nueva
                        int cantidadNueva = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la nueva cantidad."));
                        // Obtenemos la diferencia
                        int diferencia = p.getCantidad() - cantidadNueva;
                        // Enviamos la opcion '3 (modificar)' al servidor
                        dos.writeInt(3);
                        dos.flush();
                        // Enviamos el id
                        dos.writeInt(id);
                        dos.flush();
                        //Enviamos la diferencia
                        dos.writeInt(diferencia);
                        dos.flush();
                        // Recibimos la validacion
                        validacion = dis.readBoolean();
                        if (validacion) {
                            // Se acepto la modificiacon
                            p.agregar(-diferencia);
                            for (Producto pTienda : tienda) {
                                if (pTienda.getId() == id) {
                                    pTienda.agregar(diferencia);
                                }
                            }
                            llenarTablas();
                        } else {
                            JOptionPane.showMessageDialog(null, "SERVIDOR: Error al modificar el producto");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un producto");
        }
    }

    private void comprar() {
        try {
            // Enviamos la opcion '4 (compra)'
            dos.writeInt(4);
            dos.flush();
            // Recibimos la validacion
            boolean validacion = dis.readBoolean();
            if (validacion) {
                imprimirTicket();
                carrito.clear();
            } else {
                JOptionPane.showMessageDialog(null, "SERVIDOR: ERROR al afectuar la compra");
            }
            llenarTablas();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void salir() {
        try {
            dos.writeInt(5);
            dos.flush();

            dis.close();
            dos.close();
            socket.close();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recibirImagenes() {
        try {
            int i = dis.readInt();
            while (i > 0) {
                recvFile(dis, rutaCarpeta);
                i--;

                dos.writeInt(1);
                dos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recibirInventario() {
        try {
            tienda = null;
            tienda = (ArrayList<Producto>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void imprimirTicket() {
        String contenido = "";
        float total = 0;
        for (int i = 0; i < carrito.size(); i++) {
            Producto p = carrito.get(i);
            float totalProducto = (float) (p.getPrecio() * p.getCantidad());
            contenido = contenido + String.format("\t%d %s x%d --- %.2f\n", i, p.getNombre(), p.getCantidad(), totalProducto);
            total += totalProducto;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm:ss");
        System.out.println("===================== TICKET ====================");
        System.out.println("\tFECHA & HORA: " + dtf.format(LocalDateTime.now()));
        System.out.println(contenido);
        System.out.println(String.format("================== TOTAL A PAGAR: $%.2f ==================", total));
        System.out.println("======================================================\n\n");
    }

    private void recvFile(DataInputStream dis, String rutaCarpeta) {
        try {
            String nombre = dis.readUTF();
            long tam = dis.readLong();
            DataOutputStream dosFile = new DataOutputStream(new FileOutputStream(rutaCarpeta + nombre));
            long recibidos = 0;
            int l = 0;
            while (recibidos < tam) {
                byte[] b = new byte[1024];
                l = dis.read(b);
                dosFile.write(b, 0, l);
                dos.flush();
                recibidos += l;
            }
            dosFile.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}