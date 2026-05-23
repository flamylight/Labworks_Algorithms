#### 1. Таблиця переходів (завдання 2)
| Поточний стан | Slash (/) | LowerLetter (a-z) | SpecialUpper (F-K) | EOS (Кінець рядка) | ANY (Будь-який інший символ) |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **Initial** | Q1 | Error | Error | Error | Error |
| **Q1** | Error | Q1 | Q2 | Error | Error |
| **Q2** | Error | Error | Q2 | Success | Error |
| **Success** | Error | Error | Error | Error | Error |
| **Error** | Error | Error | Error | Error | Error |


#### 1. Граф (завдання 2)
```mermaid
stateDiagram-v2
    [*] --> Initial

    state "Initial\n(початковий стан)" as Initial
    state "Q1\nчитання [a-z]*" as Q1
    state "Q2\nчитання [F-K]+" as Q2
    state "Success\nуспіх" as Success
    state "Error\nпомилка" as Error

    Initial --> Q1 : /
    Initial --> Error : будь-що інше

    Q1 --> Q1 : a-z
    Q1 --> Q2 : F-K
    Q1 --> Error : /, EOS, інші

    Q2 --> Q2 : F-K
    Q2 --> Success : EOS
    Q2 --> Error : /, a-z, інші

    Success --> [*]
    Error --> [*]
```