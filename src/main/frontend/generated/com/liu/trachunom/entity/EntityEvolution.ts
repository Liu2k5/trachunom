import type EntityEvolutionId_1 from "./EntityEvolutionId.js";
import type EntityX_1 from "./EntityX.js";
interface EntityEvolution {
    id?: EntityEvolutionId_1;
    fromEntity?: EntityX_1;
    toEntity?: EntityX_1;
    description?: string;
}
export default EntityEvolution;
