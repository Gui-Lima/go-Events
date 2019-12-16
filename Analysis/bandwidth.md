# Banda

O aplicativo é focado em servidor para fazer várias ações, e o gráfico de banda mostra isso claramente. Nas telas que há requisições para pegar itens das RecyclerViews há um pico quase constante de 10kb/s. Porém as listas maiores aumentaram esse número para perto dos 15. Uma medida para filtrar listas e deleta-las depois de um tempo se vê necessária.

Na atividade do mapa(MapActivity) vê-se um pico bem maior, como esperado. Essa atividade demanda muito da rede pois carrega o mapa na primeira vez por completo, chegando a 77kb/s. O mapa fica salvo no SD, então só precisaria ser recarregado raramente(ao deslogar, por exemplo).
