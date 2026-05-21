/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author XPxTBxLLX
 */

import  com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class Sport_comunication {
        
    public static void main(String []  args){
         SerialPort port = SerialPort.getCommPort("COM7");
         
         port.setBaudRate(9600);
        port.setNumDataBits(8);
        port.setNumStopBits(1);
        port.setParity(SerialPort.NO_PARITY);

        // 3. Establece un timeout para no quedarte esperando eternamente[reference:2]
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 1000, 0);

        // 4. Intenta abrir el puerto
        if (port.openPort()) {
            System.out.println("✅ Puerto " + port.getSystemPortName() + " abierto con éxito.");
        } else {
            System.err.println("❌ Error al abrir el puerto. Revísalo.");
            return;
        }

        // 5. Configura un "oyente" que se ejecutará CADA VEZ que llegue un dato
        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }

            @Override
            public void serialEvent(SerialPortEvent event) {
                byte[] buffer = new byte[port.bytesAvailable()];
                int numRead = port.readBytes(buffer, buffer.length);
                String datosRecibidos = new String(buffer, 0, numRead).trim();
                System.out.println("📥 Lectura recibida: " + datosRecibidos);
                
                // 6. Aquí procesas el código QR y haces scraping/HTTP
                if (datosRecibidos.startsWith("http")) {
                    System.out.println("🌐 Es una URL, procesando scraping...");
                    //procesarURL(datosRecibidos);
                } else {
                    System.out.println("El dato recibido no parece ser una URL.");
                }
            }
        });

        System.out.println("🔄 Esperando lecturas... Presiona Enter para salir.");
        try { System.in.read(); } 
        catch (Exception e) { e.printStackTrace(); } 

        // 7. Cierra el puerto al salir
        port.closePort();
        System.out.println("Puerto cerrado.");
    }
}
