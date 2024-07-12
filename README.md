# README.md

## Trabalho T2 - Projeto e Otimização de Algoritmos

Este repositório contém as implementações e análises de algoritmos de Programação Dinâmica e Branch-and-Bound, conforme proposto na disciplina de Projeto e Otimização de Algoritmos.

### Objetivos do Trabalho

- Implementar e analisar algoritmos de Programação Dinâmica e Branch-and-Bound.
- Resolver problemas específicos usando esses algoritmos.
- Comparar a eficiência e a corretude das soluções implementadas.

### Problema 1: Gestão de Tarefas Semanais

#### Descrição

Suponha que você esteja gerenciando uma equipe de desenvolvedores (DEVs) que precisa escolher um trabalho para realizar a cada semana. As tarefas possíveis são divididas em duas categorias: baixa dificuldade (exemplo: criar uma API) e alta dificuldade (exemplo: criar um detector de textos elaborados pelo ChatGPT).

Cada semana oferece uma recompensa em dinheiro para as tarefas realizadas:
- **Baixa dificuldade**: \( l_i > 0 \) dinheiros.
- **Alta dificuldade**: \( h_i > 0 \) dinheiros, mas requer que a semana anterior não tenha nenhuma tarefa (preparação).

Dada uma sequência de \( n \) semanas, um plano é especificado por uma escolha de "baixa dificuldade", "alta dificuldade" ou "fazer nada" para cada uma das \( n \) semanas, com a restrição de que se uma tarefa de alta dificuldade é escolhida para a semana \( i \), então "fazer nada" deve ser escolhido para a semana \( i-1 \).

O objetivo é encontrar um plano de valor máximo.

#### Exemplo

Suponha \( n = 4 \) e os valores das recompensas são:
- \( l = [10, 1, 11, 10] \)
- \( h = [5, 50, 1000, 1] \)

A melhor solução seria:
- Semana 1: Fazer nada
- Semana 2: Tarefa de alta dificuldade (50 dinheiros)
- Semana 3: Tarefa de baixa dificuldade (11 dinheiros)
- Semana 4: Tarefa de baixa dificuldade (10 dinheiros)

Valor total do plano: 0 + 50 + 11 + 10 = 71.

### Problema 2: Problema da Mochila

#### Descrição

O problema da mochila consiste em maximizar o valor dos itens colocados em uma mochila sem ultrapassar a capacidade máxima de peso.

#### Exemplo

Dado um conjunto de itens, cada um com um peso e um valor, determine a combinação de itens que maximize o valor total sem exceder o peso máximo permitido na mochila.

### Estrutura do Relatório

O relatório do trabalho é estruturado nas seguintes seções:

#### Problema 1

1. **O Problema**:
   - Descrição detalhada do problema.
   - Exemplo ilustrativo.

2. **Resposta do item 1**:
   - Análise do algoritmo incorreto e exemplo de uma instância onde ele falha.

3. **Resposta do item 2**:
   - O Algoritmo: Descrição do algoritmo de Programação Dinâmica proposto.
   - Análise do Algoritmo: Discussão sobre a eficiência e corretude.
   - Implementação e Tempo de Execução: Detalhes sobre a implementação e análise de desempenho.

#### Problema 2

1. **O Problema**:
   - Descrição detalhada do problema da mochila.

2. **O Algoritmo**:
   - Descrição do algoritmo de Branch-and-Bound usado para resolver o problema.
   - Análise do Algoritmo: Discussão sobre a eficiência e corretude.
   - Implementação e Tempo de Execução: Detalhes sobre a implementação e análise de desempenho.

### Como Executar

Para compilar e executar os códigos:

1. Certifique-se de ter o JDK instalado.
2. Compile os arquivos Java com o comando `javac`.
3. Execute os programas usando o comando `java`.

### Contato

Para mais informações, entre em contato com os desenvolvedores.

---

Este trabalho foi realizado como parte da disciplina de Projeto e Otimização de Algoritmos, sob a orientação do professor Rafael Scopel.
