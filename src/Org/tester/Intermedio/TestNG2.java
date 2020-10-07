package Org.tester.Intermedio;

import org.testng.annotations.*;

public class TestNG2 {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Este metodo se ejecuta antes de una suite de pruebas");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Este metodo se ejecuta antes de las pruebas @test");

    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Este metodo se ejecuta antes de la clase donde estoy");
    }

    @BeforeMethod
    public void beforeMethod() {

        System.out.println("Este metodo se ejecuta antes de prueba @test");

    }

    @Test
    public void testCase1() {
        System.out.println("Caso de prueba 1");
    }

    @Test
    public void testCase2() {
        System.out.println("Caso de prueba 2");
    }
    @Test
    public void testCase3() {
        System.out.println("Caso de prueba 3");
    }

    @AfterMethod
    public void aftermethod(){
        System.out.println("Se ejecuta despues de cada metodo de prueba");
    }

    @AfterClass
    public void afterClass (){
        System.out.println("Este metodo se ejecuta despues de la clase");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("Este metodo se ejecuta despues de todas las pruebas");
    }

    @AfterSuite
    public void afterSuite (){

        System.out.println("Este metodo se ejecuta despues de una suite de pruebas");
    }




}
