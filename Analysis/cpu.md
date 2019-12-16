# CPU

Em geral o App não passou de 30% de usagem, e 70 threads. Mas esse dado pode ser deceptivo. Na verdade, na atividade do mapa, MapActivity, há uma grande usagem no começo, pois as tiles estão sendo carregadas e o mapa é grande. Em demais atividades, o número de threads não passa de 50 e a usagem perto dos 3%.

Para melhorar o gasto de cpu e de conexão, limitei o escopo inicial do mapa e o zoom mínimo e máximo. Assim, o usuário fica mais limitado em gastar sua CPU mas não perde funcionalidade, pois os eventos geralmente de um grupo são específicos de uma área não muito grande(Ex. a cidade onde o usuário mora).

Análise feita em um Samsung Note 10.
