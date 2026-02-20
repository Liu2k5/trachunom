import type ExplanationDto_1 from "./ExplanationDto.js";
import type MeaningDto_1 from "./MeaningDto.js";
interface MeaningDto {
    id?: number;
    origin?: MeaningDto_1;
    explanations?: Array<ExplanationDto_1 | undefined>;
    explanationsString?: string;
}
export default MeaningDto;
