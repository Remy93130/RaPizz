DELIMITER |

CREATE OR REPLACE TRIGGER T_Fidelite
AFTER INSERT ON Commande FOR EACH ROW
BEGIN
	UPDATE Client
	SET pointFidelite = pointFidelite + 1
	WHERE idClient = NEW.idClient;
END|

DELIMITER ;
