@SAUCE_UI_TEST_PLAYWRIGHT
Feature: Saucedemo Ui test

  Background:
    Given Saucedemo user log in Playwright

  @SAUCE_UI_TEST_PLAYWRIGHT_01
  Scenario: Ui test
    Given Check additional menu Playwright
