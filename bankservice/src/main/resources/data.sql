INSERT INTO BANK_ATM_MACHINE (ID, USER_NAME, PASSWORD, INSERT_DATE, VERSION) VALUES
  (1, 'atm1', '1234', parsedatetime('01-01-2022 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 1),
  (2, 'atm2', '4321', parsedatetime('01-01-2022 19:23:12.41', 'dd-MM-yyyy hh:mm:ss.SS'), 1);
INSERT INTO BANK_CUSTOMER (ID, NATIONAL_ID, FIRST_NAME, LAST_NAME, CUSTOMER_TYPE, INSERT_DATE, VERSION) VALUES
(1, 256541238,'behzad', 'khandzad', 'R',  parsedatetime('01-01-2022 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 1),
(2, 5465123544,'sam', 'khandzad',  'R', parsedatetime('01-01-2022 19:23:12.41', 'dd-MM-yyyy hh:mm:ss.SS'), 1);
INSERT INTO BANK_CUSTOMER_CARD (ID, ACCOUNT_NUMBER, CARD_NUMBER, BANK_CUSTOMER_ID, CVV2, ISSUE_DATE, EXPIRE_DATE ,CARD_STATE, CARD_PIN, INCORRECT_PIN, AUTH_METHOD, INSERT_DATE, VERSION) VALUES
(1, 19855484521001, 1459833336354632, 1, 1465,  parsedatetime('01-01-2021 21:47:21.39', 'dd-MM-yyyy hh:mm:ss.SS'),
    parsedatetime('01-01-2025 21:47:21.39', 'dd-MM-yyyy hh:mm:ss.SS'),'U',8521,0,'P',parsedatetime('01-01-2021 21:47:21.39', 'dd-MM-yyyy hh:mm:ss.SS'), 1),
(2, 19856116510251, 1481859818325453, 2, 4853, parsedatetime('01-01-2020 08:52:22.02', 'dd-MM-yyyy hh:mm:ss.SS'),
    parsedatetime('01-01-2024  08:52:22.02', 'dd-MM-yyyy hh:mm:ss.SS'),'U',1563,0,'F',parsedatetime('01-01-2020 08:52:22.02', 'dd-MM-yyyy hh:mm:ss.SS'), 1);
INSERT INTO BANK_CUSTOMER_FINGERPRINT (ID, FINGERPRINT, BANK_CUSTOMER_ID, INSERT_DATE, VERSION) VALUES
(1,  'csdfadsgf44sd55asd46asddsfads', 1,  parsedatetime('01-01-2022 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 1),
(2, 'dasdfg456ater5vc321gfd', 2,parsedatetime('01-01-2022 19:23:12.41', 'dd-MM-yyyy hh:mm:ss.SS'), 1);