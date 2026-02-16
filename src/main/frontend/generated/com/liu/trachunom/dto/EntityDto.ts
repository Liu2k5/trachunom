interface EntityDto {
    id?: number;
    structureId?: number;
    pronunciationId?: number;
    meaningId?: number;
    languageId?: number;
    description?: string;
    compound: boolean;
    attested: boolean;
    standardised: boolean;
    hnomString?: string;
    qnguString?: string;
    explanationsString?: string;
}
export default EntityDto;
