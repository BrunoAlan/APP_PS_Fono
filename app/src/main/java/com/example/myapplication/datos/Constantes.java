package com.example.myapplication.datos;

public abstract class Constantes {

    public static final String PERSONALIZADO = "Personalizado";
    public static final String NO_PERSONALIZADO = "No Personalizado";

    //CATEGORIAS
    public static final String PALABRA = "Palabra";
    public static final String ORACIONES = "Oraciones";
    public static final String CANCIONES = "Canciones";
    public static final String INSTRUMENTOS = "Instrumentos";
    public static final String ESTILOS_MUSICALES ="Estilos Musicales";
    public static final String VOCES_FAMILIARES = "Voces Familiares";
    public static final String RUIDO = "Ruido";

    public static final String[] CATEGORIAS = { PALABRA, ORACIONES, CANCIONES, INSTRUMENTOS, ESTILOS_MUSICALES, VOCES_FAMILIARES};

    //TIPOS DE EJERCICIOS
    public static final String IDENTIFICAR_TRES_OPCIONES = "Identificar entre 3 opciones";
    public static final String IDENTIFICAR_CINCO_OPCIONES = "Identificar entre 5 opciones";
    public static final String ESCRIBIR_LO_QUE_OYO = "Escribir lo que oyó";
    public static final String [] TIPOS_EJERCICIOS ={IDENTIFICAR_TRES_OPCIONES,IDENTIFICAR_CINCO_OPCIONES,ESCRIBIR_LO_QUE_OYO};

    //TIPOS DE EJERCICIOS ORACIONES

    public static final String COMPLETAR_ORACION_SIN_OPCIONES = "Completar oración sin opciones";
    public static final String COMPLETAR_ORACION_CON_OPCIONES = "Completar oración con opciones";
    public static final String [] TIPOS_EJERCICIOS_ORACIONES ={IDENTIFICAR_TRES_OPCIONES,IDENTIFICAR_CINCO_OPCIONES,ESCRIBIR_LO_QUE_OYO, COMPLETAR_ORACION_CON_OPCIONES, COMPLETAR_ORACION_SIN_OPCIONES};

    //SUBCATEGORIA PALABRAS
    public static final String TODAS = "Todo";
    public static final String DIAS_SEMANA = "Días de la Semana";
    public static final String NUMEROS = "Números";
    public static final String COLORES = "Colores";
    public static final String ANIMALES = "Animales";
    public static final String NOMBRES = "Nombres";
    public static final String ROPA = "Ropa";
    public static final String COMIDA = "Comida";
    public static final String LUGARES = "Lugares";
    public static final String MESES = "Meses";

    public static final String [] SUBCATEGORIAS_PALABRAS = {TODAS,DIAS_SEMANA,NUMEROS,COLORES,ANIMALES,NOMBRES,ROPA,COMIDA,LUGARES,MESES};

    public static final String [] FILTRO_PRACTICA = {TODAS,DIAS_SEMANA,NUMEROS,COLORES,ANIMALES,NOMBRES,ROPA,COMIDA,LUGARES,MESES, ORACIONES, VOCES_FAMILIARES, ESTILOS_MUSICALES, CANCIONES, INSTRUMENTOS, RUIDO};

    public static final String EJERCITACION = "Ejercitación";
    public static final String EVALUACION = "Evaluación";

}
