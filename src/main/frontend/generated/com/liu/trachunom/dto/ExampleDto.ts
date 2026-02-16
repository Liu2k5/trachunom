import type ExampleWordDto_1 from "./ExampleWordDto.js";
interface ExampleDto {
    id?: number;
    hnomString?: string;
    qnguString?: string;
    exampleWords?: Array<ExampleWordDto_1 | undefined>;
    sourceName?: string;
    sourceDescription?: string;
    sourceId?: number;
}
export default ExampleDto;
