DELIMITER |

CREATE PROCEDURE P_Menu()
BEGIN
  SELECT nomPizza, prix, nomIngredient
  FROM pizza p, ingredient i, contient c
  WHERE p.idPizza = c.idPizza
    AND i.idIngredient = c.idIngredient
  ORDER BY nomPizza, nomIngredient;
END |

DELIMITER ;
