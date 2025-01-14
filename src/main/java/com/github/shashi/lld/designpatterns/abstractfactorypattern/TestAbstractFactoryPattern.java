package com.github.shashi.lld.designpatterns.abstractfactorypattern;

public class TestAbstractFactoryPattern {
    public static void main(String[] args) {
        GUIFactory factory = new MacFactory();
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        button.paint();       // Output: Mac Button
        checkbox.paint();      // Output: Mac Checkbox
    }
}
interface Button { void paint(); }
class MacButton implements Button { public void paint() { System.out.println("Mac Button"); } }
class WinButton implements Button { public void paint() { System.out.println("Windows Button"); } }

interface Checkbox { void paint(); }
class MacCheckbox implements Checkbox { public void paint() { System.out.println("Mac Checkbox"); } }
class WinCheckbox implements Checkbox { public void paint() { System.out.println("Windows Checkbox"); } }

interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

class WinFactory implements GUIFactory {
    public Button createButton() { return new WinButton(); }
    public Checkbox createCheckbox() { return new WinCheckbox(); }
}