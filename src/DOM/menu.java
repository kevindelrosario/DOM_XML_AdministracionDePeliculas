package DOM;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import org.w3c.dom.*;

public class menu {

	public static void main(String[] args) {
	
		
		boolean llaveTodo = true;
		
		while(llaveTodo) {
			System.out.println(inicio());
			llaveTodo = respuesta();
			
		}

	}
	
	
	
/**
 * Menu de inicio
 * @return menu
 */
	public static String inicio() {
		return "	INICIO\n\n"
				+ "*****  MENU  *****"
				+ "\n--> 1.Leer un XML"
				+ "\n--> 2.Anadir nuevos elementos a un XML"
				+ "\n--> 3.Borrar elementos de un XML"
				+ "\n--> 4.Cerrar el programa"
				+ "\n****************";	
	}
	
	/**
	 * Respuesta
	 * toma el dato del menu que ingrese el usuario y lo lleva a la funcion que puede realizar lo que pide.
	 */
	public static boolean respuesta() {
		
		boolean llave = true;
		
		System.out.println("respuesta");
		Scanner sc = new Scanner(System.in);
		System.out.println(": ");
		try {
		int resp = sc.nextInt();
		
		//casos del menu
		switch(resp){
				case 1:
					leerXml();	
					break;				
				case 2:
					anadirXml();
					break;	
				case 3:
					borrarXml();
					break;	
				case 4:
					System.out.println("Cerrado...");
					llave = false;
					return llave;
					
				default:
					System.out.println("Por favor ingresa uno de los datos indicados en el menu....");
					break;	
				}
		
		} catch(InputMismatchException e) {
			System.err.println("Has introducido un dato erroneo..." + e.getMessage());		//mostrara el fallo con el mensaje indicando cual es.
		}
		
		return llave;
		
		
	}
	/**
	 * Leer XML
	 * Imprime por pantalla lo que contiene el xml
	 */
	public static void leerXml() {
		
		System.out.println("\n***********\n");
		//tomando el fichero a modificar
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		//creacion de un nuevo documentBuilderFactory
		String fichero = "pelicula.xml";		//nombre del archivo XML quiero modificar
		 
		try {
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse( new File(fichero));		
		//obtener el nodo raiz
		System.out.println("-> " + doc.getDocumentElement().getNodeName()); // nos muestra la raiz de todo el xml (nodo raiz)
		
		
		//Listar todo el documento
		NodeList cine = doc.getElementsByTagName("pelicula");   //listamos todo y vamos a recorrer con el for
		
		
		//recorrer la lista de nodos peliculas
		for (int i = 0; i < cine.getLength(); i++) {
			Node pelicula = cine.item(i);
			System.out.println("->Pelicula: ");
			if(pelicula.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) pelicula;
				
				// ----- Obtener los valores de los nombres de las peliculas
				NodeList nodo = elemento.getElementsByTagName("nombre").item(0).getChildNodes();
				Node valornodo = (Node) nodo.item(0);
				System.out.println("\t -Nombre: " + valornodo.getNodeValue());
				
				// ----- Obtener los valores de los nombres de los Autores
				NodeList nodoAutor = elemento.getElementsByTagName("autor").item(0).getChildNodes();
				Node valornodoAutor = (Node) nodoAutor.item(0);
				System.out.println("\t -Autor: " + valornodoAutor.getNodeValue());
				
				// ----- Obtener los valores de las fechas de estrenos
				NodeList nodoEstreno = elemento.getElementsByTagName("estreno").item(0).getChildNodes();
				Node valornodoEstreno = (Node) nodoEstreno.item(0);
				System.out.println("\t -Estreno: " + valornodoEstreno.getNodeValue());
			}
		}
		System.out.println("-> "+doc.getDocumentElement().getNodeName()); // nos muestra la raiz de todo el xml (nodo raiz)
		
		} catch(Exception e) {
			System.err.println("Has introducido un dato erroneo..." + e.getMessage());		//mostrara el fallo con el mensaje indicando cual es.
		}
		
		
		
	}
/**
 * Añadir nuevos datos al xml
 * En este metodo se piden datos por pantalla para añadirlos al XML
 */
    public static void anadirXml() {
    	
    	System.out.println("\n***********\n");
		//tomando el fichero a modificar
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		//creacion de un nuevo documentBuilderFactory
		String fichero = "pelicula.xml";											//nombre del archivo XML quiero modificar
		 
		try {
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse( new File(fichero));		
    	
    	System.out.println("Para añadir datos al xml por favor ingrese los datos que se le pide por pantalla...");
    	
    	//pedir datos
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre de la pelicula:");
		String nombre = sc.nextLine();
		System.out.println("Nombre del autor:");
		String autor = sc.nextLine();
		System.out.println("Fecha de estreno:");
		String estreno = sc.nextLine();
		
		//crear un elemento pelicula
		Element raizPelicula = doc.createElement("pelicula");
		doc.getDocumentElement().appendChild(raizPelicula);
		
		//crear un elemento nombre
		Element nombreElement = doc.createElement("nombre");
		Text nombreTexto = doc.createTextNode(nombre);
		raizPelicula.appendChild(nombreElement);
		nombreElement.appendChild(nombreTexto);
		
		//crear un elemento autor
				Element autorElement = doc.createElement("autor");
				Text autorTexto = doc.createTextNode(autor);
				raizPelicula.appendChild(autorElement);
				autorElement.appendChild(autorTexto);
		
				//crear un elemento estreno
				Element estrenoElement = doc.createElement("estreno");
				Text estrenoTexto = doc.createTextNode(estreno);
				raizPelicula.appendChild(estrenoElement);
				estrenoElement.appendChild(estrenoTexto);
				
				System.out.println("¿Quieres guardar los cambios realizados?"
						+ "\n1. Si"
						+ "\ncualquier boton para no guardar.");			//Pregunta para guardar o no los datos
				int guardar = sc.nextInt();
				
				if (guardar == 1 ) {
				Source source = new DOMSource(doc);
				Result result = new StreamResult("pelicula.xml");
				Transformer trans = TransformerFactory.newInstance().newTransformer();
				trans.transform(source, result);
				
				}else {
					System.out.println("Perfecto, los datos no se guardaran");
				}
				
				
		}catch(Exception e){
			System.err.println("Has introducido un dato erroneo..." + e.getMessage());		//mostrara el fallo con el mensaje indicando cual es.
		}
	}
/**
 * Borrar datos del XML
 * En este metodo se borran datos del XML
 */
    public static void borrarXml() {
    	System.out.println("\n***********\n");
    	
    	
		//tomando el fichero a modificar
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		//creacion de un nuevo documentBuilderFactory
		String fichero = "pelicula.xml";											//nombre del archivo XML quiero modificar
		Scanner sc = new Scanner(System.in);
		
		
    	try {
    		
    		DocumentBuilder builder = dbf.newDocumentBuilder();
    		Document doc = builder.parse( new File(fichero));		
        	
    		
    		//borrar
			System.out.println("Inserta el nombre de la pelicula que quieres borrar");
			String nombreBorrar = sc.nextLine();
			
			NodeList peliculas2 = doc.getElementsByTagName("pelicula");
			
			//recorrer la lista de nodos pelicula2
			for (int i = 0; i < peliculas2.getLength(); i++) {
				
				Node pelicula2 = peliculas2.item(i);
				
				if(pelicula2.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) pelicula2;
					
					NodeList nodoNombre = elemento.getElementsByTagName("nombre").item(0).getChildNodes();
					Node valornodo = (Node) nodoNombre.item(0);		
					
					/*
					NodeList nodoAutor = elemento.getElementsByTagName("autor").item(0).getChildNodes();
					Node valornodoAutor = (Node) nodoAutor.item(0);
					
					NodeList nodoFecha = elemento.getElementsByTagName("fecha").item(0).getChildNodes();
					Node valorFecha = (Node) nodoFecha.item(0);
					*/
					if(valornodo.getNodeValue().equals(nombreBorrar)) {
						pelicula2.getParentNode().removeChild(pelicula2);
					}
					
				}
				
   	
			}
			System.out.println("¿Quieres guardar los cambios realizados?"
					+ "\n1. Si"
					+ "\ncualquier boton para no guardar.");			//Pregunta para guardar o no los datos
			int guardar = sc.nextInt();
			
			if (guardar == 1 ) {
			Source source = new DOMSource(doc);
			Result result = new StreamResult("pelicula.xml");
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			trans.transform(source, result);
			
			}else {
				System.out.println("Perfecto, los datos no se guardaran");
			}
    	}
    	catch(Exception e){
			System.err.println("Has introducido un dato erroneo..." + e.getMessage());		//mostrara el fallo con el mensaje indicando cual es.
		}
    	
    }	 	

	
}
