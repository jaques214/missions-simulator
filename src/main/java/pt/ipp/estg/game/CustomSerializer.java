package pt.ipp.estg.game;

import com.google.gson.*;
import pt.ipp.estg.interfaces.IResult;
import structures.ArrayOrderedList;
import java.lang.reflect.Type;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão de Felgueiras<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática
 * ED - Estruturas de Dados
 * </h3>
 * <p>Description: Classe que implementa uma versão personalizada do exportador da livraria GSON</p>
 * Nome: Jaques Alberto Ferreira Resende
 * Número: 8190214
 * <p>
 * Nome: Bruno Miguel Pinto Costa
 * Número: 8170110
 */
public class CustomSerializer implements JsonSerializer<Mission> {

    /**
     * Método que cria um objeto personalizado para a exportação de cada missão
     * @param mission objeto com as informações relativas à missão
     * @param typeOfSrc objeto proveniente da livraria GSON
     * @param context objeto proveniente da livraria GSON
     * @return retorna um elemento do tipo JSON para ser exportado
     */
    @Override
    public JsonElement serialize(Mission mission, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("missionID", new JsonPrimitive(mission.getMissionID()));
        jsonObject.add("version", new JsonPrimitive(mission.getVersion()));

        JsonObject target = new JsonObject();
        target.add("division", new JsonPrimitive(mission.getTarget().getDivision()));
        target.add("type", new JsonPrimitive(mission.getTarget().getType()));

        jsonObject.add("target", target);

        ArrayOrderedList<IResult> results = mission.getResults();
        JsonArray someJsonArray = new JsonArray(results.size());

        for(int i = 0; i < results.size(); i++){
            IResult result = results.get(i);
            JsonObject resultObj = new JsonObject();
            resultObj.add("points", new JsonPrimitive(result.getPoints()));
            resultObj.add("state", new JsonPrimitive(result.stateToString()));
            resultObj.add("pathToTarget", new JsonPrimitive(result.getPathToTarget()));
            resultObj.add("pathToExit", new JsonPrimitive(result.getPathToExit()));
            resultObj.add("stepCount", new JsonPrimitive(result.getStepCount()));
            someJsonArray.add(resultObj);
        }

        jsonObject.add("results", someJsonArray);
        return jsonObject;
    }
}
