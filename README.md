# Missions Simulator

### **Tema**

A fictícia IMF (Improbable Mission Force) pediu um simulador de missões para o seu melhor agente, o intrépido António Cruzeiro (a.k.a., Tó Cruz). 
Dado o perigo que estas missões acarretam, é necessário, tendo em conta as informações que são recolhidas com recurso a espiões, informadores, entre outros, uma aplicação que permita ao Tó Cruz simular as ações a tomar quando tentar as incursões aos edifícios dos criminosos de modo a resgatar pessoas, bens, ou armas de destruição maciça, etc.
Como as informações (estrutura de edifício, localização dos criminosos e suas armas, e a localização do alvo a atingir) são por vezes contraditórias e não são obtidas em simultâneo,  a aplicação obtem as informações do edifício através de um ficheiro em formato JSON (ver imagem). 

![mapa](https://user-images.githubusercontent.com/72866928/155573838-7773f41b-2379-4149-89ff-6ad3e4bc1d43.jpg)

Este ficheiro contém um código de missão (cod-missao) e uma versão (versao) para poder testar vários cenários tendo em conta as informações correntes. A chave edifício contém todas as divisões do edifício a infiltrar. A chave ligacoes apresenta as ligações entre as divisões do edifício. A chave inimigos contem os dados correntes sobre os criminosos que se 
encontram dentro do edifício, nomeadamente, o seu nome (nome), o seu poder (poder) e a divisão (divisao) que protege. O poder do criminoso representa a quantidade de pontos de vidas que este criminoso retira ao Tó Cruz caso se depara com este criminoso. A chave entradas-saidas representa as divisões que podem ser utilizadas como entradas ou saídas do edifício. Finalmente, a chave alvo contém a informação sobre o alvo, nomeadamente a divisão (divisao) em que se encontra e o seu tipo (tipo). No início o Tó Cruz tem 100 pontos de vida.

**Funcionalidades:**

- Carregar missões com diferentes versões a partir de um ficheiro JSON.
- Dois tipos de simulação: manual ou automática:
  - A simulação manual questiona qual a entrada a considerar e a partir daí, iterativamente, pede a divisão para a qual deseja movimentar-se (notifica quando chega ao alvo). 
Quando entra num aposento que tem um criminoso, os pontos de poder do criminoso são retirados aos pontos de vida do Tó. A missão termina com sucesso quando o Tó saí do edifício com o alvo. A missão termina sem sucesso quando o Tó perde todos os seus pontos de vida, ou sai do edifício sem o alvo. 
  - A simulação automática indica qual o trajeto que permita ao jogador atingir o alvo com o maior número de pontos restantes, considerando todas as entradas possíveis. Deve considerar o trajeto de volta e apresentar ao Tó, se é possível realizá-lo sem comprometer o sucesso da missão. Uma missão tem sucesso quando o Tó Cruz consegue sair do edifício,  passando pelo alvo com 100 pontos de vida.
- Permitir visualizar na consola o mapa carregado. 
- Apresentar, para uma missão selecionada, os resultados das simulações realizadas.
- Exportar para um ficheiro JSON, os trajetos elaborados em cada simulação manual.

**Estruturas de Dados utilizadas:**

- LinkedList
- ArrayList
- Queue
- Stack
- BinaryTree
- Graph
- Heap
