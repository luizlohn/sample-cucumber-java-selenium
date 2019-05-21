Feature: Contact form

   Scenario: Send a contact form
    Given I access tje Prime Control page
    When I click on the option Formulario de Contato
    And I fill All field
    And I click on the button Enviar
    Then should display a success message
 