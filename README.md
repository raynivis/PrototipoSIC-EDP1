# Trabalho EDP I

**Grupo:** Eduardo Alves De Oliveira Freitas, Gustavo Cortez de Paula, Mayara Marques De Oliveira Gonçalves e Ray Santiago Del Negro

**Importante:**

Adicionar o plugin de json nas maquinas

Estudar mais **Recursão**

**Para desenhos do slide:** draw.io / app.driagrams.net / **Miro** app 



**Divisões e Cronograma:**


|**Atividade**|**Dificuldade**|**Integrantes**|**Data de Entrega Prevista**|**Data de Entrega**|
| - | - | - | :-: | - |
|Estruturas de Dados|Trabalhoso|Todos|01/07||
|Slides do CP 1|Trabalhoso|Todos|04/04|05/04||
|Slides do CP 2|Trabalhoso|Todos|05/06||
|Slides do TF|Trabalhoso|Todos|03/07||
|Classes|Médio|Ray, Cortez e Mayara|02/04|28/03||
|Operação OBR 1|Médio|Eduardo e Mayara|||
|Operação OBR 2|Médio|Eduardo e Ray|||
|Operação OBR 3|Trabalhoso|Todos|||
|Operação OBR 4|Trabalhoso|Ray, Cortez e Mayara|||
|Operação OBR 5|Simples|Cortez e Eduardo|||
|Operação OBR 6|Trabalhoso|Eduardo, Cortez e Mayara|||
|Arquivos para testes|Médio|Mayara e Ray|||


**Legenda:**

**CP:** CheckPoint.

**TF:** Trabalho Final.

**OBR:** Obrigatória.

**Operação OBR 1:** Carregamento do arquivo de Espelho do Registro Geral das Unidades da Federação (ERGUF).

**Operação OBR 2:** Persistência dos dados/Recuperação de dados. 


**Operação OBR 3:** Busca por número de registro geral/RG. 


**Operação OBR 4:** Relatórios.


**Operação OBR 5:** Medição de tempo das operações.



**Operação OBR 6:** Interface com o usuário.


**Diagram de Classe**:



![diagramav2](https://github.com/gustavo-cortez/trabalhoED/assets/154099929/6784e52d-f2fc-4bf7-8af2-1f20c1b5d93a)


1. **Informações dos cidadãos**

   Desenvolvimento, cada **Cidadão** deve apresentar no mínimo esses **dados**: 
   
   
   
   **Nome completo (máximo de 70 caracteres) : String**
   
   Exemplo: “Ray Santiago Diva Negro”
   
   Data de nascimento (máximo de 10 caracteres) : **Date** Exemplo: “31/07/2006”
   
   
   
   **Naturalidade (máximo de 30 caracteres) : Classe**
   
   Exemplo: São 2 Campos, Cidade e Estado “Castilho”
   
   “SP”
   
   ***Observações:***
   
   *Ray: Seria Legal na hora de Relatório o campo ficar: “Castilho-SP”*
   
   *Cortez: -> verdade*
   
   *May: Deveríamos colocar correção para o preenchimento das informações? Por exemplo, tem o modelo da data de nascimento para o preenchimento: 00/00/0000, mas caso a pessoa insira 23/02/12, o programa corrigirá automaticamente ou o usuário deve inserir novamente?*
   
   
   
   **Números do registro geral do cidadão**
   
   **CPF (Máximo e mínimo de 11 + 3 (símbolos) = 14 caracteres/inteiro)**
   
   **RG do Estado (0 caracteres/inteiro no mínimo e 14 + 8 (símbolos) = 22 caracteres/inteiro no máximo)**
   
   **: Classe**
   
   Exemplo: São 2 Campos, Rg e CPF
   
   “12345678911”
   
   “123456789”
   
   ***Observações:***
   
   *Ray: O mínimo de caracteres de RG é zero porque é possível uma pessoas não ter o RG em nenhum estado*
   
   *Sobre os 13 a 14 digitos do RG: [https://pt.stackoverflow.com/questions/121593/quantidade-de-caracteres-do-cpf-cnpj-e-rg*](https://pt.stackoverflow.com/questions/121593/quantidade-de-caracteres-do-cpf-cnpj-e-rg)*
   
   *Podíamos fazer uma verificação se o cpf do mesmo indivíduo em estados diferentes são iguais e colocar uma mensagem de tentativa de falsificação ou erro no cadastro.*
   
   *May: Acho interessante, mas teria que ver qual tipo de busca é o mais adequado pra não pesar muito*
   
   *O formato do rg vai depender do estado.*
   
   *Seria legal no relatório estar no formato de “xxx.xxx.xxx-xx” e no rg vai depender do estado e do período feito (o que é mais complicado 🙁)*
   
   *May: Acho interessante, é melhor pra organizar também*
   
   
   
   
   **Registros gerais emitidos (de 0 a 27): Inteiro**
   
   Exemplo:
   
   2
   
   Nesse exemplo é contado a partir do arquivo que o indivíduo possui dois registros de RG, por exemplo em São Paulo e na Bahia
   
   *Ray: Como no exemplo abaixo do arquivo, não possui um campo para a quantidade de RGs feitos, então imagino que seja um contador interno, talvez uma função para modificar o valor de RGs Encontrados vai ser implementado*
   
   Pode ser feito mais dados para um determinado indivíduo, e isso vai partir de ideias que podem facilitar o desenvolvimento da solução.

2. **Operações desejadas**

Operações **obrigatórias**:

1. **Carregamento do arquivo de Espelho do Registro Geral das Unidades da Federação (ERGUF):**

   Ao ser **iniciado**, o programa é necessário declarar no “**buffer**” os arquivos com os **registros** a serem cadastrados, os arquivo de **ERGUF** é um arquivo no formato **JSON**;

   A operação **não** deve ser acessada **durante a execução do programa**; Pode ser importado **um ou mais** arquivos ERGUF;

   Importado no i**nício via argumento de programa**, **primeira** coisa a ser processada; **Nem sempre** é **obrigatório** que o programa **importe** um arquivo ERGUF;

   **Exemplos** de Arquivos ERGUF estão **anexados** no **AVA**; ***Observações:***

   *Ray: Acredito que por causa do caso de uma pessoa ter sido registrada em dois ou mais estados diferentes, deverá ser feito pelo menos 27 (estados existentes) campos (strings) para registrar os RGs de cada cidadão.*

   *Acho que antes de inserir um cidadão devemos olhar na lista toda se tem alguém com o mesmo CPF, para assim só cadastrar o RG*

   *Edu: Achei um guia de como importar Json e transformar em string ou objeto ([https://medium.com/@marciosindeaux/lendo-arquivos-json-no-java-de-forma-simplificada-693e9c89a982 ](https://medium.com/@marciosindeaux/lendo-arquivos-json-no-java-de-forma-simplificada-693e9c89a982))*

2. **Persistência dos dados:**

   Ao **iniciar ou encerrar** o programa, os **dados devem estar de acordo com as execuções feitas no programa**, até a última **execução**;
   
   **Importações novas e alteração de dados de algum cidadão** deve estar **atualizada** ao **fim** do programa;
   
   As **alterações** devem ser **permanentes**, mesmo que o programa se encerre **(ignorando os casos de erro)**.
   
   Uma das soluções, é usar **arquivos de dados**, esse arquivo pode ser em **binário ou texto** (vamos ver qual é mais funcional), **priorizando o tempo de otimização**.
   
   ***Observações:***
   
   *Ray: Não sei se entendi bem, mas nesse método de arquivo de dados, o manter dados no início deverá ser mantido no buffer pela última execução (ou seja vai ter q escrever sempre que vai importar os últimos dados) ou deverá ser feito de forma automática??*
   
   *Edu: Acho que a maneira mais simples de fazer em java seria com persistência via arquivo de texto, que é “tipo” um BD mesmo que o java aceita, é bem mais fácil de fazer do que implementar um BD, tipo MUITO mais fácil. Uma outra opção seria “escrever” um novo JSON quando encerrar o programa e ele abrir esse JSON completo sempre que iniciasse o programa (já de forma automática) porém acredito que isso daria mais trabalho do que fazer por meio de arquivo.*

3. **Busca por número de registro geral/RG:**

   Uma busca realizada para encontrar **determinado cidadão** em base a seu **RG**;
   
   “uma operação muito realizada diariamente.”, ou seja tem que ser muito **eficiente**;
   
   Devemos fazer **vários testes**;
   
   Mostrar **todas as informações cadastradas de um cidadão**, incluindo **todos os RGs** junto com a **unidade da federação que foi feito cada cadastro**.
   
   ***Observações:***
   
   *Ray: Mais tarde volto aqui com os resultados de buscas, para ver o pior caso de alguns jeitos, mas queria transformar os rg em inteiros e fazer busca binária;*

4. **Relatórios**

   **Grupo de operações** para gerar um relatório
   
   O **relatório** será gerado após o usuário informar a faixa etária desejada; talvez um readline “idade a idade” ou dois separados: idade, idade
   
   O cálculo da idade será **arredondado**, ou seja **2024** - O ano de nascimento;
   
   exemplo nasci em 2004 em julho, tenho 19 ainda, mas no sistema vai contar como eu tendo 20
   
   Os nomes **deverão ser listados pelo estado que nasceram**
   
   O relatório deve conter os seguintes dados:
   
   - número do registro geral,
   - nome completo
   - data de nascimento
   - naturalidade
   
   Nessa **ordem**;
   
   O relatório deve ser ordenado pelo nome dos cidadãos (talvez usar uma lista auxiliar?);
   
   *May: Encadeamento externo seria eficiente aqui?


   **Observações:***
   
   *Ray: Meu Deus.*
   
   *Cortez: muito é massa doido.*

6. **Medição de tempo das operações**

   Os **alunos da disciplina** que vão indicar qual a melhor **estrutura de dados para a solução do problema;**
   
   A informação do **tempo gasto** em cada **operação** seja **explícita**;
   
   “Para os que **não entendem de programaçã**o consigam **diferenciar** (as **estruturas**) e **comparar** facilmente o **desempenho dos protótipos.**”;
   
   **Final** de cada **operação** realizada (itens anteriores) o **protótipo** deve mostrar o **tempo gasto**, em **segundos**;
   
   ***Observações:***
   
   *Ray: Acho que o cortez disse que tem uma função do java que já faz isso.*
   
   *May: Encontrei um vídeo e um site que falam sobre essa função:*
   
   [*Java - Pegar o tempo de execução de uma função ](https://www.youtube.com/watch?v=nhGG6DWfRLw)*/ https://pt.stackoverflow.com/questions/431058/o-que-o-número-retornado-por-system-currenttimemillis-re![](Aspose.Words.84381239-6c9b-410e-9213-6a7f99a8eec7.002.png)*
   
   *presenta*

7. **Interface com o usuário:**

   Importante que o sistema seja **fácil de ser usado por usuários sem tanto conhecimento de programação**;
   
   É necessário **apresentar**, pelo menos, **esboços da Interfaces** nos **Checkpoints.**
   
   Apresentar uma i**nterface agradável** ao usuário que o **permita realizar as operações listadas** acima **sem muito esforço**;
   
   **Possibilidades**:
   
   - Criar um programa que interaja com o usuário via **terminal**, através de **menus** ou como um interpretador de **comandos;**
   - Programa com **interface** gráfica, com o uso de **janelas e botões**;
   - Versão **híbrida**, que permita as **duas formas** de operação, conforme o desejado;
   
   A **escolha** da interface gráfica deve levar em conta o **contexto da aplicação** e a **necessidade** de **avaliar** as estruturas de dados.
   
   ***Observações:***
   
   Operações **Adicionais**:
   
   ???

**3. Requisitos da disciplina**

**Estruturas de dados:**

Escolhidas **cinco estruturas** que devem ser **implementadas**:

- **Lista encadeada;** 

**Anotações e ideias:**

Lista linear usada (talvez): tipo um encadeamento externo (da tabela hash)

Uma lista com os estados -> uma lista em alfabeto -> lista com as pessoas as iniciais Uma lista com ordenado pelo ano de nascimento -> uma lista ordenado pelo CPF

- **Tabela de dispersão;**

**Anotações e ideias:**

- **Árvore binária de busca;**

**Anotações e ideias:**

*May: Só pra salvar essas video aulas ->*

[Aula 21 - Árvore Binária - Estruturas de Dados com Java](https://www.youtube.com/watch?v=WAYwUG9iQ44&list=PLTLAlheiUm5FRR5BNn4iBFwzYHiNq2Iv2&index=20)![](Aspose.Words.84381239-6c9b-410e-9213-6a7f99a8eec7.003.png)

- **Árvore AVL;**

**Anotações e ideias:**

- **Árvore digital.**

**Anotações e ideias:**

A **estrutura** deve servir como base de um **protótipo para organizar os dados dos cidadãos**. Isso quer dizer que, no final do semestre, serão entregues **cinco protótipos**. Nada impede que seu protótipo **utilize mais** do que **uma estrutura** em **conjunto**, **mas** a **estrutura** que organiza as **informações dos cidadãos** deve ser as que estão **listadas**

As **características** de cada **estrutura fazem parte da avaliação** e devem ser bem **justificadas** de acordo com o **contexto do problema**.

**Linguagem de programação**

Implementados na linguagem de programação de sua **preferência**;

Um protótipo escrito em **Java**, além de bem escrito, deve aplicar corretamente o **conceito de orientação a objetos.**

***Observações:***

**Grupo**

Apesar da atividade ser realizada em **grupo**, a **avaliação** será feita **individualmente**.

Não há nada de errado em **dividir as responsabilidades** por partes do projeto: modelagem das estruturas, interface com o usuário, implementação, testes, etc.

Porém, é importante que **todos os envolvidos** saibam sobre todos os detalhes da implementação e da avaliação das **estruturas utilizadas**.

***Observações:***

**Avaliação**

Teremos **três datas de entrega** onde os grupos terão que realizar **uma breve apresentação para a turma** sobre **o desenvolvimento do trabalho**.

**Checkpoint 1** (06/04) - *sábado*; 
**Checkpoint 2** (07/06); 
**Entrega final** (05/07).

Checkpoints

Os **checkpoints** servem para apresentar o que **já foi desenvolvido**, e promover uma **discussão com os outros grupos sobre as propostas apresentadas**, a apresentação dos checkpoints, cada grupo deve **criar um conjunto de slides que aborde**, obrigatoriamente, os **seguintes itens**:

- O que foi **desenvolvido**, e por **quem**;
- **Dificuldades** encontradas;
- **Decisões tomadas** para a implementação das **estruturas** até o momento (tipo de lista encadeada; detalhes da tabela de dispersão; linguagem de programação; modelagem do tipo de dado; estruturas auxiliares; arquivo de dados; etc.);
- Como foram **tratadas as operações** em relação à **organização das estruturas;** 
- **Esboço da interface com o usuário**. A interface final **não precisa** estar **implementada**, mas deve ser apresentado, pelo menos, uma ilustração de como **ficará a interação do usuário com o programa**;
- **Planejamento do grupo para o desenvolvimento** do **restante** do projeto, com **estimativa de datas** e **responsáveis por cada tarefa**;
- **Referências** utilizadas;
- **Outras** informações **relevantes**, caso julguem necessário.

***Observações:***

Apresentação final

A apresentação final deve ser **mais completa e contemplar**, além **dos detalhes de implementação**, uma **avaliação das estruturas quanto seu desempenho na solução do problema**

- O que foi **desenvolvido**, e por **quem**;
- **Dificuldades encontradas** no desenvolvimento do trabalho;
- **Decisões** tomadas para a *implementação das estruturas** (tipo de lista encadeada; detalhes da tabela de dispersão; linguagem de programação; modelagem do tipo de

  dado; estruturas auxiliares; arquivo de dados; etc.); 

- Como foram **tratadas as operações em relação a organização das estruturas**; 
- Funções que **não conseguiram implementar** e/ou **funções extras implementadas**; 
- Implementação da **sexta estrutura (*bônus*)**; 
- Como foram pensados e **realizados os testes de avaliação**; 
- **Resultado dos testes** e a **indicação da melhor estrutura implementada pelo grupo**;

  **Referências** utilizadas; 

- **Outras informações relevantes**, caso julguem necessário.

No **dia da apresentação final**, o grupo deve **disponibilizar o programa indicado como a melhor opção para testes no laboratório**.

O **intuito é a avaliação da interface com o usuário e a definição**, em conjunto, do **melhor programa implementado** para o problema proposto.

Vale ressaltar que os **testes e comparações práticas entre as diferentes estruturas são uma das partes mais importantes do trabalho** e merecem um **tratamento especial**. ***Observações:***

**Bônus**

Valendo até **3.0** pontos (**caso** não alcance a **nota máxima (10)**):

As implementações solicitadas no trabalho foram de algumas **estruturas de dados estudadas na disciplina.**

Porém, **muitas outras estruturas de dados** podem ser utilizadas para organizar as **informações como solicitado**.

Esse **bônus consiste em implementar um sexto protótipo**, com uma outra **estrutura de dados**.

A estrutura escolhida pode ser uma **estrutura bem definida**, tirada de **algum livro** ou que **estudamos na disciplina mas não implementamos**, **assim como pode** ser uma **combinação de estruturas de dados já estudadas** que venham **potencializar as operações realizadas pelo programa**.

***Observações:***
