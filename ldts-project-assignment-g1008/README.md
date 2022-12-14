## LDTS<10><8> - TETRIS

Este jogo é uma implementação fiel em java, do clássico Tetris, com o objetivo de encaixar peças, que descem ao longo da tela,  de diferentes formatos, capazes de girar sobre o próprio eixo em apenas quatro posições. Um jogador aumenta a sua pontuação ao completar uma linha, que desaparece ao ser completada e a linha superior desce ocupando a sua posição.

O projeto foi desenvolvido por *Adam Nogueira* (up202007519@fe.up.pt), *Ana Sofia Costa* (up202007602@fe.up.pt) e *Inês Oliveira* (up202103343@fe.up.pt).

### CARACTERÍSTICAS IMPLEMENTADAS

**Rodar as peças:** O utilizador do jogo deve usar as setas do teclado para girar as peças que descem.

**Completar linhas:** Ao completar uma linha, esta desaparece e a linha de cima ocupa a sua posição.

**Velocidade da peça:** As peças descem a uma velocidade constante. No entanto, o jogador pode acelerá-la usando a seta para baixo do teclado.

**Guardar uma peça:** O jogador pode guardar uma peça e jogar novamente com a peça anteriormente guardada carregando na tecla C. Se ainda nao tiver nenhuma peça guardada, avança para a próxima.

**Drop instantâneo:** O jogador pode descer uma peça ao máximo possível ao pressionar a barra de espaço.

**Ver as próximas peças:** Durante o jogo é possível ver as 4 peças que serão jogadas a seguir.

**Menu:** Quando o jogo é iniciado, aparece o Menu. O jogador tem de selecionar a letra "S" do teclado para iniciar o jogo.

**Instruções:** No menu inicial, caso o jogador queira ter acesso às instruções do jogo, pode selecionar a letra "I" e irá ser disponibilizado um menu de instruções.

**Sair do jogo:** A qualquer momento do programa o utilizador tem a possibilidade de carregar na tecla "Q" para sair instantaneamente do jogo.

**Pontos ao completar linhas:** Ao completar uma linha o jogador aumenta a sua pontuação. Se completar mais do que uma linha, de uma so vez, a pontuação será maior.

**Pontuações:** Valores base : 1 linha = 40 pontos, 2 linhas= 100 pontos, 3 linhas = 300 pontos, 4 linhas = 1200 pontos; A pontuação será valor base*nível, com bónus de 50% caso complete linhas com peças seguidas.

**Diferentes níveis:** À medida que o jogador vai ganhando pontos, os níveis vão aumentando, consequentemente a velocidade com que a peça desce também irá aumentar. O nível que o utlizador se encontra é indicado na tela.

**Game over:** Se o jogador deixar que as peças chegam ao topo da tela, perde e o jogo termina. Na tela irão ser revelados os pontos finais e o nível em que o jogador se encontrava.

**Nova tentiva:** No final do jogo, após perder o jogador tem a oportunidade de selecionar a opção para reiniciar o jogo.

 ![Tetris_Implementado](docs/gif_implementado.gif) 


| Menu | Game Over |
|:----------:|:---------:|
| ![Menu_Implementado](docs/StartMenu.png) | ![GameOver](docs/gameOver.png) |