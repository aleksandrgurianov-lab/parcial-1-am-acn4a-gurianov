# Informe de Proyecto - Parcial 1: Aplicación "ArgenBooking"

**Estudiante:** Gurianov Aleksandr  
**Curso:** ACN4AV - Aplicaciones Móviles  
**Profesor:** Sergio Daniel Medina  
**Repositorio GitHub:** https://github.com/aleksandrgurianov-lab/parcial-1-am-acn4a-gurianov

---

## 1. Descripción de la Pantalla Seleccionada
Para la entrega de este primer parcial, se ha seleccionado una pantalla crítica y de alta fidelidad dentro del ecosistema de la futura aplicación de reservas: la **Pantalla de Detalle de Alojamiento (Property Detail Screen)**.

La aplicación "ArgenBooking" está destinada a la gestión y reserva de lofts premium en Buenos Aires. La pantalla desarrollada expone de manera realista el inmueble **"Palermo Luxury Loft"**, permitiendo al usuario interactuar con un cotizador en tiempo de ejecución para calcular el precio total de su estadía.

---

## 2. Flujo de Uso de la Pantalla (User Flow)
1. **Ingreso y Visualización:** El usuario accede a la pantalla de detalle, donde visualiza la identidad de la marca (Logotipo en formato vectorial) y la navegación por migas de pan ("INICIO / PALERMO LUXURY LOFT").
2. **Exploración de Contenido Realista:** El usuario observa una fotografía de alta calidad del loft, acompañada por las especificaciones técnicas del alojamiento (capacidad, Wi-Fi, aire acondicionado) y una descripción comercial detallada.
3. **Interacción con el Cotizador:** En la sección central, el usuario interactúa con un calendario nativo de Material Design para seleccionar el rango de fechas de su estadía. Al confirmar las fechas, la interfaz calcula automáticamente las noches y actualiza los valores.
4. **Procesamiento de Reserva:** Al presionar el botón principal "Reservar", la aplicación ejecuta lógica dinámica en Java, renderizando un comprobante de éxito en tiempo de ejecución.

---

## 3. Justificación Técnica de Componentes Utilizados (Android XML)
La interfaz fue estructurada de manera limpia y eficiente en el archivo `activity_detail.xml`, cumpliendo estrictamente con la pauta de diseño solicitada:

*   **ConstraintLayout (Raíz):** Utilizado como el lienzo contenedor principal para asegurar una distribución adaptativa y fluida en diferentes resoluciones de pantalla.
*   **ScrollView:** Envuelve todo el contenido descriptivo y el formulario de cotización, garantizando el desplazamiento vertical del usuario sin generar desbordamientos (overflow).
*   **LinearLayout (Vertical):** Es el hijo único obligatorio del ScrollView, encargado de apilar de forma secuencial y ordenada los elementos de la propiedad (Logo, Título, Imagen, Descripción y Tarjeta).
*   **LinearLayout (Horizontal):** Utilizado para estructurar las filas complejas del cotizador, como la disposición simétrica de los campos de entrada de datos.
*   **TextView:** Empleados para la representación de títulos, etiquetas informativas, precios y detalles de facturación.
*   **EditText:** Configurados con tipos de entrada específicos (`inputType="date"` y `inputType="number"`) para capturar la interacción de fechas del usuario.
*   **Button ("Reservar"):** El elemento de acción principal que desencadena la lógica de negocio al finalizar la configuración de la estancia.

---

## 4. Comportamiento Dinámico y Eventos (Código Java)
Para alcanzar el puntaje máximo y cumplir con los requisitos obligatorios de interacción, se desarrollaron los siguientes componentes en `DetailActivity.java`:

1.  **Evento de Cierre/Retorno (Event Listener):** El TextView de las migas de pan posee un `OnClickListener` asociado que ejecuta el método `finish()`, permitiendo al usuario destruir la actividad actual y regresar fluidamente a la pantalla previa.
2.  **Inclusión Dinámica de Elementos (Runtime Java UI):** Al hacer clic en el botón "Reservar", el código Java genera dinámicamente un objeto `TextView` en la memoria del dispositivo, configurando su texto ("¡Reserva procesada con éxito!"), tamaño y color institucional, insertándolo directamente dentro del contenedor `dynamicInvoiceContainer` sin haber estado presente en el XML original.
3.  **Lógica del Cotizador de Fechas:** Se integró el componente oficial `MaterialDatePicker` para la selección de rangos de fechas, automatizando el cálculo matemático de las noches y refrescando los textos de precios instantáneamente.

---

## 5. Organización de Recursos y Buenas Prácticas 
Se evitó por completo el uso de valores "hardcodeados", organizando el proyecto bajo estándares profesionales:
*   `strings.xml`: Centraliza todos los textos legibles en idioma español.
*   `colors.xml`: Define la paleta premium de la aplicación, utilizando tonos oscuros (`bg_dark`) y el naranja corporativo (`orange_premium`) .
*   `dimens.xml`: Controla los márgenes, paddings (en DP) y tamaños de fuentes (en SP) respetando los lineamientos de Material Design .
*   **Historial de Git:** Los cambios se subieron de manera fragmentada mediante una secuencia estructurada de 6 commits utilizando la convención de **Conventional Commits** (`feat:`, `chore:`, `docs:`).