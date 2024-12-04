import java.util.*;

public class ProyectoUnidad4 {
    
    static class Ciudad {
        String nombre;
        int distancia;
        
        Ciudad(String nombre, int distancia) {
            this.nombre = nombre;
            this.distancia = distancia;
        }
    }
    
    public static void dijkstra(Map<String, List<Ciudad>> grafo, String ciudadInicio, String ciudadDestino) {
        
        Map<String, Integer> distancias = new HashMap<>();
        
        Map<String, String> camino = new HashMap<>();
        
        PriorityQueue<Ciudad> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.distancia));
        

        for (String ciudad : grafo.keySet()) {
            distancias.put(ciudad, Integer.MAX_VALUE);
        }
        distancias.put(ciudadInicio, 0);
        
        camino.put(ciudadInicio, null);
        
        pq.offer(new Ciudad(ciudadInicio, 0));
        
        while (!pq.isEmpty()) {
            Ciudad ciudadActual = pq.poll();
            
            if (ciudadActual.nombre.equals(ciudadDestino)) {
                break;
            }
            
            for (Ciudad vecino : grafo.get(ciudadActual.nombre)) {
                int nuevaDistancia = distancias.get(ciudadActual.nombre) + vecino.distancia;
                
                if (nuevaDistancia < distancias.get(vecino.nombre)) {
                    distancias.put(vecino.nombre, nuevaDistancia);
                    camino.put(vecino.nombre, ciudadActual.nombre);
                    pq.offer(new Ciudad(vecino.nombre, nuevaDistancia));
                }
            }
        }
        

        if (distancias.get(ciudadDestino) == Integer.MAX_VALUE) {
            System.out.println("No hay un camino disponible entre las ciudades.");
        } else {
            System.out.println("Distancia más corta: " + distancias.get(ciudadDestino));
            
            List<String> ruta = new ArrayList<>();
            for (String ciudad = ciudadDestino; ciudad != null; ciudad = camino.get(ciudad)) {
                ruta.add(ciudad);
            }
            Collections.reverse(ruta);
            System.out.println("Ruta: " + String.join(" -> ", ruta));
        }
    }
    
    public static void main(String[] args) {

        Map<String, List<Ciudad>> grafo = new HashMap<>();
        
        grafo.put("A", Arrays.asList(new Ciudad("B", 2), new Ciudad("C", 4)));
        grafo.put("B", Arrays.asList(new Ciudad("A", 2), new Ciudad("C", 1), new Ciudad("D", 7)));
        grafo.put("C", Arrays.asList(new Ciudad("A", 4), new Ciudad("B", 1), new Ciudad("D", 3)));
        grafo.put("D", Arrays.asList(new Ciudad("B", 7), new Ciudad("C", 3)));
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ciudad de inicio: ");
        String ciudadInicio = scanner.nextLine();
        System.out.print("Ciudad de destino: ");
        String ciudadDestino = scanner.nextLine();
        
        dijkstra(grafo, ciudadInicio, ciudadDestino);
    }
}
