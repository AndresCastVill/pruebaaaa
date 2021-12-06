Feature: Compra de producto en PCFactory

  Background:
    Given Se inicializa navegador

  @Test_01
  Scenario: Form
    When ir a la pagina de llenado
    And llenar campos
      |Biology      | Sports      |
      |Maths        | Reading     |
      |English      | xd          |

  @Test_02
  Scenario: Libros
    When consultar libros
      |Git Pocket Guide                     |
      |Speaking JavaScript                  |
      |Programming JavaScript Applications  |
      |Eloquent JavaScript, Second Edition  |
      |Learning JavaScript Design Patterns  |
    And validar detalle del libro
    And cerrar sesion


  @Test_03
  Scenario: Compra de producto
    When se agrega al carrito de compras
      |CPU             | 42781      |
      |PLACA           | 43073      |
      |RAM             | 43163      |
      |RAM             | 43163      |
      |DISCO SSD       | 43822      |
      |tarjeta de video| 43993      |
      |monitor         | 43076      |
      |gabinete        | 41395      |
      |ventilador      | 43328      |
      |teclado         | 42231      |
      |mouse           | 42249      |
    And validar carrito