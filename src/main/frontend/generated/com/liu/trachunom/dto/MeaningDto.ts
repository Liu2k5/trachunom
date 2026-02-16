import type ExplanationDto_1 from "./ExplanationDto.js";
interface MeaningDto {
    id?: number;
    explanations?: Array<ExplanationDto_1 | undefined>;
    explanationsString?: string;
}
export default MeaningDto;
