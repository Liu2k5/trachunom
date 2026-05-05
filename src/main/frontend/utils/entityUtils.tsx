import {useEffect, useMemo, useState} from 'react';
import EntityX from "Frontend/generated/com/liu/trachunom/entity/entity/EntityX";
import {
    EntityMapper,
    EntityService,
    ExampleService,
    PronunciationEvolutionService,
    PronunciationService,
    SourceService,
    StructureService, StructureComponentService, StructureDescriptionService
} from "Frontend/generated/endpoints";
import EntityEvolutionDto from "Frontend/generated/com/liu/trachunom/dto/EntityEvolutionDto";
import {
    getEntityEvolutionsByToEntityId,
    getGridTemplateColumnsByStructureId, getStructureDtoByStructureId
} from "Frontend/generated/EntityDetailEndpoint";
import type StructureDto from "Frontend/generated/com/liu/trachunom/dto/StructureDto";
import StructureComponentDto from "Frontend/generated/com/liu/trachunom/dto/StructureComponentDto";
import * as StructureEndpoint from "Frontend/generated/StructureEndpoint";
import Pronunciation from "Frontend/generated/com/liu/trachunom/entity/pronunciation/Pronunciation";
import Meaning from "Frontend/generated/com/liu/trachunom/entity/meaning/Meaning";
import Source from "Frontend/generated/com/liu/trachunom/entity/Source";
import PronunciationEvolutionDto from "Frontend/generated/com/liu/trachunom/dto/PronunciationEvolutionDto";
import Structure from "Frontend/generated/com/liu/trachunom/entity/structure/Structure";
import StructureComponent from "Frontend/generated/com/liu/trachunom/entity/structure/StructureComponent";
import {GlyphAdjustment} from "Frontend/utils/glyphAdjustment";

export {
    HnomQngu as HnomQngu,
    HnomStringByExampleId as HnomStringByExampleIdComponent,
    DrawEvolution as DrawEvolution,
    AnalyseStructure as AnalyseStructure,
    DrawPronunciationEvolution as DrawPronunciationEvolution,
    DrawMeaningEvolution as DrawMeaningEvolution,
    DrawEntityYear as DrawEntityYear,
    DrawStructureInitialiser as DrawStructure,
};

const HnomQngu = ({entityId, markedId}: {entityId: number | undefined, markedId: number}): JSX.Element => {
    const [entity, setEntity] = useState<EntityX | undefined>();
    const [hnomString, setHnomString] = useState<string>('');
    const [qnguString, setQnguString] = useState<string>('');

    useEffect(() => {
        if (!entityId) return;

        const loadData = async () => {
            const fetchedEntity = await EntityService.findById(entityId) ?? undefined;
            const fetchedHnom = await EntityService.getHnomStringById(entityId) ?? '';
            const fetchedQngu = await EntityService.getQnguStringById(entityId) ?? '';

            setEntity(fetchedEntity);
            setHnomString(fetchedHnom);
            setQnguString(fetchedQngu);
        };

        loadData();
    }, [entityId]);

    const content = (
        <div
            style={{
                textAlign: 'center',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                color: entity?.id === markedId ? 'blue' : (entity?.attested ? 'black' : 'grey'),
                position: 'relative',
                margin: '0'
            }}
        >
            <p style={{fontSize: '0.6em', margin: '0px', lineHeight: '1em', position: 'absolute', top: '-1em', transform: 'scale(0.5, 1)', width: '200%'}}>
                {qnguString}
            </p>
            <div style={{fontSize: '1em', margin: '0px', lineHeight: '1em', height: 'fit-content'}}>
                {entity?.compound ? (
                    <p
                        style={{
                            margin: '0',
                        }}
                    >
                        {hnomString}
                    </p>
                    ) :
                    (
                        <div
                            style={{
                                // width: '200px',
                                // height: '200px',
                                width: '1em',
                                height: '1em',
                                // aspectRatio: '1 / 1',
                                display: 'flex',
                                alignItems: 'stretch',
                                justifyContent: 'stretch',
                                // marginLeft: '-1%',
                                // marginTop: '-1%',
                                padding: '0',
                                // position: 'absolute',
                                // backgroundColor: 'lightgray',
                            }}
                        >
                            <DrawStructureInitialiser structureId={Number.parseInt(entity?.structureId ?? 0 as unknown as string)}/>
                        </div>
                    )
                }
            </div>
        </div>
    );

    return entity?.attested ? (
        <div
        style={{
            width: 'max-content',
            // minWidth: '30px',
        }}
        >
            <a
                href={'/entity/' + entity.id}
                style={{textDecoration: 'none', color: 'black'}}
                title={entity.explanationsString}
            >
                {content}
            </a>
        </div>
    ) : (
        <div style={{color: 'grey'}}>
            {content}
        </div>
    );
};

// Component version that uses hooks
const HnomStringByExampleId = ({exampleId}: {exampleId: number | undefined}): JSX.Element => {
    const [exampleString, setExampleString] = useState<string>('');

    useEffect(() => {
        if (!exampleId) {
            setExampleString('');
            return;
        }

        const loadExample = async () => {
            const fetchedExample = await ExampleService.getHnomStringByExampleId(exampleId) ?? '';
            setExampleString(fetchedExample);
        }
        loadExample();
    }, [exampleId]);

    return <span>{exampleString}</span>;
};

function DrawEvolution ({evolutions}: {evolutions: EntityEvolutionDto[] | null | undefined})  {
    return (
        <div style={{
            display: 'flex',
            gap: '20px',
            overflowX: 'auto',
            paddingBottom: '10px',
        }}>
            {evolutions?.map((evolution, index) =>
                evolution ? (
                    <EvolutionRow evolution={evolution} key={index}></EvolutionRow>
                ) : null
            )}
        </div>
    );
}

function EvolutionRow({evolution}: { evolution: EntityEvolutionDto | null | undefined }): JSX.Element {
    const [evolutions, setEvolutions] = useState<EntityEvolutionDto[] | null>(null);
    useEffect(() => {
        getEntityEvolutionsByToEntityId(evolution?.fromEntityId)
            .then((results: any) => {
                setEvolutions(results ?? null);
            })
            .catch((error: any) => console.error('Error fetching evolutions:', error));
    }, [evolution?.fromEntityId]);
    const recursiveValue = evolution?.fromEntity ? <DrawEvolution evolutions={evolutions}/> : undefined;

    return (
        <div
            style={{
                minWidth: '100px',
                // textAlign: 'center',
                padding: '10px',
                borderRadius: '6px',
            }}
        >
            <p
                style={{
                    fontSize: '14px',
                    color: 'darkcyan',
                    marginBottom: '20px',
                }}
            >
                {evolution?.description}
            </p>
            <div
                style={{
                    backgroundColor: '#f0f0f0',
                }}
            >
                <div
                    style={{
                        textDecoration: 'none',
                        color: 'black',
                    }}
                >
                    <div>
                        <HnomQngu entityId={evolution?.fromEntityId} markedId={0}/>
                    </div>
                    <p>{evolution?.fromEntity?.explanationsString}</p>
                </div>
            </div>
            <div
                style={{
                    fontSize: '16px',
                    color: '#333',
                }}
            >
                {recursiveValue}
            </div>
        </div>
    )
}

function AnalyseStructure({structure}: {structure : StructureDto | undefined }): JSX.Element {
    const [structureComponents, setStructureComponents] = useState<StructureComponentDto[]>([]);
    const [gridTemplateColumns, setGridTemplateColumns] = useState<string>("");

    useEffect(() => {
        if (!structure?.id) {
            setStructureComponents([]);
            return;
        }

        // Fetch structure components
        StructureEndpoint.getStructureComponentsByStructureId(structure.id)
            .then((data: (StructureComponentDto | undefined)[] | undefined) => {
                const filtered = (data || []).filter((c): c is StructureComponentDto => c !== undefined);
                setStructureComponents(filtered);
            })
            .catch((error: any) => console.error('Error fetching structure components:', error));

        // Fetch grid template columns
        getGridTemplateColumnsByStructureId(structure.id)
            .then((data) => {
                setGridTemplateColumns(data != undefined ? data : "");
            })
            .catch((error) => console.error('Error fetching grid template:', error));
    }, [structure?.id]);

    if (!structure || structureComponents.length === 0) {
        return <div></div>;
    }

    let components: JSX.Element[] = [];
    structureComponents.forEach((component) => {
        if (component != undefined) {
            components.push(<StructureRow key={component?.structureComponentId} component={component}/>);
        }
    });

    return (
        <div style={{
            borderSpacing: "0px",
            display: "grid",
            gridTemplateColumns: gridTemplateColumns,
            gridGap: "1px",
        }}
             data-here='aaa'
        >
            {components.map((component) => component)}
        </div>
    );

}

function StructureRow({component}: { component: StructureComponentDto; }): JSX.Element {
    const [structureComponent, setStructureComponent] = useState<StructureDto | null>(null);

    useEffect(() => {
        getStructureDtoByStructureId(component.structureComponentId)
            .then((data) => setStructureComponent(data ?? null))
            .catch((error) => console.error('Error fetching structure:', error));
    }, [component.structureComponentId]);

    const recursiveValue = structureComponent ? <AnalyseStructure structure={structureComponent}/> : null;

    return (
        <div style={{alignItems: "start", verticalAlign: "top"}}>
            <div style={{borderSpacing: "0px"}}>
                <div style={{
                    backgroundColor: (component.classificationId == 1 ? "red" :
                        (component.classificationId == -1 ? "blue" : "grey")),
                    margin: "0px auto auto auto",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    height: "40px",
                }}>
                    <a
                        style={{
                            textDecoration: "none",
                            color: "white",
                            fontWeight: "bold",
                            fontFamily: "sans-serif",
                            fontSize: "18px",
                        }}
                       href={"/search?query=" + component.structureComponentCharacterString}
                    >
                        {component.structureComponentCharacterString + (component.quantity ? (component?.quantity > 1 ? "×" + component.quantity : "") : "") || ''}
                    </a>
                </div>
                <div>
                    {recursiveValue}
                </div>
            </div>
        </div>
    );
}

function DrawPronunciationEvolution({pronunciationId}: {pronunciationId: number | undefined}): JSX.Element {
    const [pronunciation, setPronunciation] = useState<Pronunciation | null>(null);
    useEffect(() => {
        PronunciationService.findById(pronunciationId ?? undefined).then(data => setPronunciation(data ?? null))
            .catch(error => console.error('Error fetching pronunciation:', error));
    }, [pronunciationId]);

    if (!pronunciation) {
        return <div></div>;
    }

    const ancestors = <DrawPronunciationAncestors pronunciationId={pronunciationId}/>;
    const descendants = <DrawPronunciationDescendants pronunciationId={pronunciationId}/>;
    return <div
        style={{
            display: 'inline-flex',
            gap: '10px',
            padding: '10px',
            // justifyContent: 'center',
            alignItems: 'center',
        }}
    >
        {ancestors}
        <div style={{
            padding: '10px',
            gap: '10px',
            borderLeft: '2px solid black',
            margin: '0 auto',
            color: 'blue',
        }}>
            {pronunciation.string}
        </div>
        {descendants}
    </div>
}

function DrawPronunciationAncestors({pronunciationId}: {pronunciationId: number | undefined}): JSX.Element {
    const [ancestors, setAncestors] = useState<PronunciationEvolutionDto[] | null>(null);
    useEffect(() => {
        if (!pronunciationId) {
            setAncestors(null);
            return;
        }

        PronunciationEvolutionService.findByToPronunciationId(pronunciationId ?? undefined)
            .then(data => data?.filter(evolution => evolution !== undefined))
            .then(data => EntityMapper.toPronunciationEvolutionDtoList(data ?? []))
            .then(data => data?.filter(dto => dto !== undefined))
            .then(data => setAncestors(data ?? null))
            .catch(error => console.error('Error fetching pronunciation evolutions:', error));
    }, [pronunciationId]);

    if (!ancestors || ancestors.length === 0) {
        return <div></div>;
    }

    return (
        <div

        >
            {ancestors.map((ancestor, index) => (
                <div
                    key={index}
                    style={{
                        display: 'flex',
                    }}
                >
                    <DrawPronunciationAncestors pronunciationId={ancestor.fromPronunciationId}/>
                    <div
                        style={{
                            display: 'flex',
                            flexDirection: 'column',
                            gap: '10px',
                            padding: '10px',
                            borderLeft: '2px solid black',
                        }}
                    >
                        {ancestor.fromPronunciationString} →
                    </div>
                </div>
            ))}
        </div>
    );
}

function DrawPronunciationDescendants({pronunciationId}: {pronunciationId: number | undefined}): JSX.Element {
    const [descendants, setDescendants] = useState<PronunciationEvolutionDto[] | null>(null);
    useEffect(() => {
        if (!pronunciationId) {
            setDescendants(null);
            return;
        }

        PronunciationEvolutionService.findByFromPronunciationId(pronunciationId ?? undefined)
            .then(data => data?.filter(evolution => evolution !== undefined))
            .then(data => EntityMapper.toPronunciationEvolutionDtoList(data ?? []))
            .then(data => data?.filter(dto => dto !== undefined))
            .then(data => setDescendants(data ?? null))
            .catch(error => console.error('Error fetching pronunciation evolutions:', error));
    }, [pronunciationId]);

    if (!descendants || descendants.length === 0) {
        return <div></div>;
    }

    return (
        <div
            style={{
                display: 'flex',
                flexDirection: 'column',
                gap: '10px',
                padding: '10px',
                borderLeft: '2px solid black',
            }}
        >
            {descendants.map((descendant, index) => (
                <div
                    key={index}
                >
                    <div>
                        → {descendant.toPronunciationString}
                    </div>
                    <DrawPronunciationDescendants pronunciationId={descendant.toPronunciationId}/>
                </div>
            ))}
        </div>
    );
}

function DrawMeaningEvolution({meaning}: {meaning: Meaning | undefined}): JSX.Element {
    if (!meaning) {
        return <div></div>;
    }

    return (
        <>
            <div style={{paddingLeft: '16px'}}>
                {meaning?.explanations?.map((explanation, idx) =>
                    explanation ? (
                        <div
                            key={explanation.id ?? idx}
                            style={{
                                marginBottom: '20px',
                                padding: '15px',
                                background: '#f9f9f9',
                                borderRadius: '6px',
                            }}
                        >
                            <div
                                style={{
                                    fontSize: '16px',
                                    color: '#333',
                                    marginBottom: '10px',
                                    fontWeight: '500',
                                }}
                            >
                                {idx + 1}. {explanation.description ?? ''}
                            </div>
                        </div>
                    ) : null
                )}
            </div>
            {meaning.origin &&
                <div
                    style={{
                        textAlign: 'center',
                        fontSizw: '14px',
                    }}
                >
                    ↑
                </div>
            }
            <DrawMeaningEvolution meaning={meaning?.origin}/>
        </>
    );
}

const colors = [
    '000000', '000055', '0000AA', '0000FF', '005500', '005555', '0055AA', '0055FF',
    '00AA00', '00AA55', '00AAAA', '00AAFF', '00FF00', '00FF55', '00FFAA', '00FFFF',
    '550000', '550055', '5500AA', '5500FF', '555500', '555555', '5555AA', '5555FF',
    '55AA00', '55AA55', '55AAAA', '55AAFF', '55FF00', '55FF55', '55FFAA', '55FFFF',
    'AA0000', 'AA0055', 'AA00AA', 'AA00FF', 'AA5500', 'AA5555', 'AA55AA', 'AA55FF',
    'AAAA00', 'AAAA55', 'AAAAAA', 'AAAAFF', 'AAFF00', 'AAFF55', 'AALFAA', 'AAFFFF',
    'FF0000', 'FF0055', 'FF00AA', 'FF00FF', 'FF5500', 'FF5555', 'FF55AA', 'FF55FF',
    'FFAA00', 'FFAA55', 'FFAAAA', 'FFAAFF', 'FFFF00', 'FFFF55', 'FFFFAA', 'FFFFFF'
];

function DrawEntityYear({entityId}: {entityId: number | undefined}): JSX.Element {
    const startYear = 1000;
    const endYear = 2000;
    const [sources, setSources] = useState<Source[] | null>(null);
    useEffect(() => {
        SourceService.findByEntityId(entityId ?? undefined)
            .then(data => data?.filter(source => source !== undefined))
            .then(data => setSources(data ?? null))
            .catch(error => console.error('Error fetching sources:', error));
    }, [entityId]);
    return (
        <div
            style={{
                display: 'flex',
                alignItems: 'center',
                gap: '10px',
            }}
        >
            {startYear}
            <div
                style={{
                    display: 'flex',
                    width: '100%',
                    position: 'relative',
                }}
            >
                <div
                    style={{
                        height: '20px',
                        width: '100%',
                        backgroundColor: 'lightgrey',
                        border: '2px solid black',
                        borderRadius: '10px',
                        boxSizing: 'border-box',
                    }}
                />
                {sources?.map((source, index) => {
                    const fromPercentage = ((source.startYear ?? startYear) - startYear) / (endYear - startYear);
                    const toPercentage = ((source.endYear ?? endYear) - startYear) / (endYear - startYear);
                    let colorValue = '#' + colors[Math.round(Math.random() * colors.length)];
                    return (
                        <div
                            key={index}
                            style={{
                                position: 'absolute',
                                left: `calc(${fromPercentage * 100}%)`,
                                width: `calc(${(toPercentage - fromPercentage) * 100}%)`,
                                height: '20px',
                                backgroundColor: colorValue,
                                border: '2px solid black',
                                borderRadius: '10px',
                                boxSizing: 'border-box',
                            }}
                            title={source.name + ' (' + (source.startYear ?? '?') + ' - ' + (source.endYear ?? '?') + ')'}
                        />
                    );
                }
                )}
            </div>
            {endYear}
        </div>
    );
}

var structureTypes = ['⿰', '⿲', '⿱', '⿳' , '⿸', '⿺', '⿹', '⿽', '⿵', '⿷', '⿶', '⿼', '⿴', '⿻'];
var verticalGroup = ['⿱', '⿳'];
var horizontalGroup = ['⿰', '⿲'];
var wrapGroup = ['⿸', '⿺', '⿹', '⿽', '⿵', '⿷', '⿶', '⿼', '⿴'];
var tripleGroup = ['⿳', '⿲'];
var wrapCentreGroup = ['⿵', '⿷', '⿶', '⿼', '⿴'];
let wrapCornerGroup = ['⿸', '⿺', '⿹', '⿽'];
var stackGroup = ['⿻'];

async function calcStructureWidthHeight(structureId: number | undefined): Promise<[number, number]> {
    if (!structureId) return [1, 1];

    var structure: Structure | undefined;
    var structureComponents: StructureComponent[] | undefined = [];
    var structureComponentResults: Structure[] | undefined = [];
    var output: [number, number] = [0, 0];

    structure = await StructureService.findById(structureId);

    // console.log(structureId + " " + structure?.structureType?.description + " " + structure?.characterString);
    // case of chu tuong hinh
    if (!structure?.structureType) return [structure?.width ?? 1, structure?.height ?? 1];

    var structureTypeDescription =  structure.structureType.description;
    structureComponents = await StructureComponentService.findByStructureId(structure.id)
        .then(data => data?.filter(o => o != undefined))
        .then(data => data?.map(o => {
            var tempArr = [];
            for (let i = 0; i < (o?.quantity ?? 0); i++) {
                tempArr.push(o);
            }
            return tempArr;
        })
            .flat());
        // .then(data => data?.filter(o => o.structureClassification?.id != 0)) // components from variants are ignored
    structureComponentResults = structureComponents
        ?.map(o => o.structureComponent)
        .filter(o => o != undefined);

    if (structureComponentResults && structureTypeDescription) {
        // console.log("finding structure components for " + structure.characterString + "(" + structureComponentResults.length + "): " + structureComponentResults.map(o => o.characterString + " "));
        await (async () => {
            const promises = structureComponentResults.map((o, index) => {
                if (!structureComponents?.at(index)?.structureClassification?.id) return calcStructureWidthHeight(undefined);
                return calcStructureWidthHeight(o.id);
            })
                .flat()
                .filter(o => o != undefined)
                .map(o => o);
            const results: [number, number][] = await Promise.all(promises);
            // console.log(results);
            output = aggregateStructureWidthHeight(structureTypeDescription, results);
        })();

    }
    if (structure.width) output = [structure.width, output[1]];
    if (structure.height) output = [output[0], structure.height];
    // console.log(structure.characterString + " " +  structure.width + ", " + structure.height);

    // console.log('here is the output of ' + structure.characterString + ': '+ output.at(0) + ", "  + output.at(1));
    return output;
}

function aggregateStructureWidthHeight(structureTypeDescription: string, results : [number, number][]): [number, number] {
    if (results.length == 0) return [1, 1];

    var output: [number, number] = [0, 0];

    if (([] as string[]).concat(...verticalGroup, ...horizontalGroup, ...wrapCentreGroup, ...stackGroup).includes(structureTypeDescription)) {
        // console.log(output[0] + ", " + output[1] + "  fr " + structureTypeDescription);
        // console.log(results[0]);
        // console.log(results[1]);
        results.map(o => {
            if (verticalGroup.includes(structureTypeDescription)) {
                output = [max(output[0], o[0]), sum(output[1], o[1])];
            } else if (horizontalGroup.includes(structureTypeDescription)) {
                output = [sum(output[0], o[0]), max(output[1], o[1])];
            } else if (wrapCentreGroup.includes(structureTypeDescription)) {
                output = [sum(output[0], o[0]), sum(output[1], o[1])];
            } else if (stackGroup.includes(structureTypeDescription)) {
                output = [max(output[0], o[0]), max(output[1], o[1])];
                // console.log(output[0] + ", " + output[1] + "   dm");

            }
        });
    } else {
        const stComp = [results?.at(0)?.at(0) ?? 1, results.at(0)?.at(1) ?? 1];
        const ndComp = [results.at(1)?.at(0) ?? 1, results.at(1)?.at(1) ?? 1];
        switch (structureTypeDescription) {
            // wrap components will be recursive until returning values, so ill solve the others (horizon, vertical)
            case '⿸':
                output = [max(stComp[0], ndComp[0] + 1), max(stComp[1], ndComp[1] + 1)];
                break;
            case '⿺':
                output = [max(stComp[0], ndComp[0] + 1), max(stComp[1], ndComp[1] + 1)];
                break;
            case '⿹':
                output = [max(stComp[0], ndComp[0] + 1), max(stComp[1], ndComp[1] + 1)];
                break;
            case '⿽':
                output = [max(stComp[0], ndComp[0] + 1), max(stComp[1], ndComp[1] + 1)];
                break;
        }
    }
    return output;
}

function max(x: number | undefined, y: number | undefined) {
    return (x ?? 0) >= (y ?? 0) ? (x ?? 0) : (y ?? 0);
}

function sum(x: number | undefined, y: number | undefined) {
    return (x ?? 0) + (y ?? 0);
}

function DrawStructureInitialiser({ structureId }: { structureId: number | undefined }): JSX.Element {
    return <DrawStructure structureId={structureId} fontSize={[1, 1]} parentStructureType={''} index={-1} key={structureId}/>
}

function DrawStructure({ structureId, fontSize , parentStructureType, index}: { structureId: number | undefined, fontSize: [number, number], parentStructureType: string, index: number }): JSX.Element {
    const [structure, setStructure] = useState<Structure | undefined>(undefined);
    useEffect(() => {
        StructureService.findById(structureId).then(o => setStructure(o));
    }, []);
    const [descriptionStructure, setDescriptionStructure] = useState<Structure | undefined>(undefined);
    useEffect(() => {
        StructureDescriptionService.findByStructureId(structure?.id)
            .then(o => setDescriptionStructure(o?.descriptionStructure));
    }, [structure]);

    const structureType = structure?.structureType;
    const [structureComponents, setStructureComponents] = useState<StructureComponent[] | undefined>(undefined);
    const [structures, setStructures] = useState<Structure[] | undefined>(undefined);

    // load components when structureId changes
    useEffect(() => {
        let fetchedId = descriptionStructure?.id ?? structureId;

        // console.log("fetchedId: " + descriptionStructure?.id + " " + structureId);

        if (!fetchedId) { setStructureComponents(undefined); return; }
        StructureComponentService.findByStructureId(fetchedId)
            .then(data => data?.filter(o => o != undefined))
            .then(data =>
                data?.sort((o1, o2) => (o1.position ?? 0) - (o2.position ?? 0)))
            .then(filtered => setStructureComponents(filtered ?? undefined))
            .catch(err => console.error(err));
    }, [descriptionStructure?.id, structureId]);

    // update structures when structureComponents changes
    useEffect(() => {
        if (!structureComponents) { setStructures(undefined); return; }
        const arr = structureComponents
            .map(o => o.structureComponent)
            .filter((s): s is Structure => s !== undefined);
        setStructures(arr);
    }, [structureComponents]);

    var description = descriptionStructure?.structureType?.description ?? structure?.structureType?.description;
    var firstStructure = structures?.at(0);
    var firstStructureTypeDescription = firstStructure?.structureType?.description;
    var output: (string | number)[];
    const [descriptionOfFirstStructure, setDescriptionOfFirstStructure] = useState<Structure | undefined>(undefined);
    useEffect(() => {
        StructureDescriptionService.findByStructureId(structures?.at(0)?.id)
            .then(o => setDescriptionOfFirstStructure(o?.descriptionStructure));
    }, [structures]);

    const [firstStructureComponents, setFirstStructureComponents] = useState<Structure[] | undefined>(undefined);
    useEffect(() => {
        // wait for these 2 value to prevent undefined result
        if (!descriptionOfFirstStructure?.id && !firstStructure?.id) return;

        StructureComponentService.findByStructureId(descriptionOfFirstStructure?.id ?? firstStructure?.id)
            .then(data => data?.filter(o => o != undefined))
            .then(data => data?.map(o => o.structureComponent))
            .then(data => data?.filter(o => o != undefined))
            .then(data => setFirstStructureComponents(data));
    }, [firstStructure, descriptionOfFirstStructure]);
    var structureIds = structures?.map(o => o.id).filter(o => o != undefined);

    // 3 truong hop
    // 1: don thuan cau tao long khong co cau tao con (逐)
    // 2: cau tao long co cau tao con nhung trong do khong co cau tao long (翹 co 堯 thuoc cau tao doc)
    // 3: co cau tao long chua cau tao long (𱑐 co 逐 long 什)
    // => 1: khong thay doi vi tri, chi can dat kich thuoc cau tao index = 0 la 100% width height
    // => 2: sap xep, phan bo thanh cac cau tao phan bo doc/ngang
    // => 3: xu li khac nhau:
    // 𱑐 => cau tao long + cau tao ngang (辵 long (豕 va 什))

    // case 1
    // if (wrapGroup.includes(description ?? 'null') &&
    //     !structureTypes.includes(firstStructureDescription ?? 'null')) {
        output= [description ?? '⿰', ...(structureIds ?? [])]; // ⿰ is default
    // }
    // case 2
    if (wrapGroup.includes(description ?? 'null') &&
        structureTypes.includes(firstStructureTypeDescription ?? 'null') &&
        !wrapGroup.includes(firstStructureTypeDescription ?? 'null')) {
        // 𥙩
        if (description === '⿺' && firstStructureTypeDescription === '⿰') {
            output= ['⿰', (firstStructureComponents?.at(0)?.id ?? 0),
                    '⿺', (firstStructureComponents?.at(1)?.id ?? 0),
                    (structureIds?.at(1)) ?? 0];
        }
    }
    else if (wrapGroup.includes(description ?? 'null') &&
        structureTypes.includes(firstStructureTypeDescription ?? 'null') &&
        wrapGroup.includes(firstStructureTypeDescription ?? 'null')) {
        // 逐 ⿶什
        if (description === '⿶' && firstStructureTypeDescription === '⿺') {
            output= ['⿺', (firstStructureComponents?.at(0)?.id ?? 0),
                '⿰', (structureIds?.at(1)) ?? 0,
                (firstStructureComponents?.at(1)?.id ?? 0)];
        }
    }

    if (
        // !structureTypeId
        structure?.character
    ) {
        return <GlyphAdjustment structureId={structureId ?? 0} structureType={parentStructureType ?? ''} blankColour={'white'} fontSize={fontSize} index={index} key={structureId}/>;
    }

    return (
        <div
            style={{
                width: '1em',
                height: '1em',
                display: 'flex',
            }}
        >
            <PaintStructureTree input={output} fontSize={fontSize} key={structureIds?.at(0) ?? 0}/>
        </div>
    );

}

function PaintStructureTree({input, fontSize}: {input: (string | number)[], fontSize: [number, number]}): JSX.Element {
    var structureType = input[0] as string;
    const inputKey = JSON.stringify(input);
    const splitSequences =
    useMemo(() => splitStructureSequence(input), [inputKey]);
    const [sizeList, setSizeList]= useState<[number, number][]>(Array.of());
    const [totalSize, setTotalSize] = useState<[number, number]>([0, 0]);
    const [firstStructure, setFirstStructure] = useState<Structure | undefined>(undefined);useEffect(() => {
        // console.log('split sequences size: ' + splitSequences.length);
        // console.log('split sequences size: ' + splitSequences.length);
        var tempSizeList: [number, number][] = [];
        (async () => {
            for (let i = 0; i < splitSequences.length; i++) {
                // console.log('split sequence: ' + splitSequences[i]);
                tempSizeList.push(await aggregateStructureTreeWidthHeight(splitSequences[i]));
            }
        })();
        setSizeList(tempSizeList);
    }, [splitSequences]);
    useEffect(() => {
        aggregateStructureTreeWidthHeight(input).then(o => setTotalSize(o));
    }, [input]);
    useEffect(() => {
        // the first sequence is always an id so i must guarantee that it is the first case above only
        // that the first component is already defined in the database
        StructureService.findById(Number.parseInt(splitSequences[0] as unknown as string))
            .then(data => {
                // console.log('set the first structure: ' + data?.characterString);
                setFirstStructure(data);
            });
        // calcStructureWidthHeight(firstStructure?.id).then(data => setFirstStructureSize(data));
    }, [splitSequences[0]]);

    // var aggregatedSize = aggregateStructureWidthHeight(description, sizeList);



    let flexDirection = 'row';
    let justification = '';
    let alignment = '';

    switch (structureType) {
        case '⿰': flexDirection = 'row'; break;
        case '⿱': flexDirection = 'column'; break;
        //⿰⿲⿱⿳⿸⿺⿹⿽⿵⿷⿶⿼⿴⿻
        case '⿲': flexDirection = 'row'; break;
        case '⿳': flexDirection = 'column'; break;
        case '⿸': flexDirection = ''; justification = 'end'; alignment = 'end'; break;
        case '⿺': flexDirection = ''; justification = 'end'; alignment = 'start'; break;
        case '⿹': flexDirection = ''; justification = 'start'; alignment = 'end'; break;
        case '⿽': flexDirection = ''; justification = 'start'; alignment = 'start'; break;
        case '⿵': flexDirection = ''; justification = 'center'; alignment = 'end'; break;
        case '⿷': flexDirection = ''; justification = 'end'; alignment = 'center'; break;
        case '⿶': flexDirection = ''; justification = 'center'; alignment = 'start'; break;
        case '⿼': flexDirection = ''; justification = 'start'; alignment = 'center'; break;
        case '⿴': flexDirection = ''; justification = 'center'; alignment = 'center'; break;
        case '⿻': flexDirection = ''; justification = 'stretch'; alignment = 'stretch'; break;
    }

    // always is from the first component in wrapping structures
    // if the first component of the wrapping structure is already defined in the database
    let innerPercentSize = [(firstStructure?.innerWidth ?? 1) / (firstStructure?.width ?? 1) * fontSize[0],
        (firstStructure?.innerHeight ?? 1) / (firstStructure?.height ?? 1) * fontSize[1]];

    // rescale width/height to 100% for the opposite structure type (vertical versus horizontal)
    let percentWidthList = sizeList.map(o => ('⿱⿳'.includes(structureType)) ? 1 : (o[0] / totalSize[0]));
    let percentHeightList = sizeList.map(o => ('⿰⿲'.includes(structureType)) ? 1 : (o[1] / totalSize[1]));

    // rescale to 100% for both width and height when the structure type is wrapping the other
    if (wrapGroup.includes(structureType)) {
        percentWidthList[0] = 1;
        percentHeightList[0] = 1;
        percentWidthList[1] = innerPercentSize[0];
        percentHeightList[1] = innerPercentSize[1];
    } else if (stackGroup.includes(structureType)) {
        percentWidthList = [1, 1];
        percentHeightList = [1, 1];
    }

    // console.log(description);
    // console.log('total size: ' + totalSize);
    // console.log('size list: ' + sizeList.map(o => '[' + o[0] + ', ' + o[1] + "]"));
    // console.log('width list: ' + percentWidthList);
    // console.log('height list ' + percentHeightList);

    let marginTop= 0;
    let marginLeft = 0;
    let marginRight = 0;
    let marginBottom = 0;

    if (wrapGroup.includes(structureType)) {
        switch (justification) {
            case 'start':
                marginLeft = 0;
                marginRight = fontSize[0] - percentWidthList[1];
                break;
            case 'center':
                marginLeft = (fontSize[0] - percentWidthList[1]) / 2;
                marginRight = (fontSize[0] - percentWidthList[1]) / 2;
                break;
            case 'end':
                marginLeft = fontSize[0] - percentWidthList[1];
                marginRight = 0;
                break;
            case 'stretch':
                marginLeft = 0;
                marginRight = 0;
                break;
        }
        switch (alignment) {
            case 'start':
                marginTop = 0;
                marginBottom = fontSize[1] - percentHeightList[1];
                break;
            case 'center':
                marginTop = (fontSize[1] - percentHeightList[1]) / 2;
                marginBottom = (fontSize[1] - percentHeightList[1]) / 2;
                break;
            case 'end':
                marginTop = fontSize[1] - percentHeightList[1];
                marginBottom = 0;
                break;
            case 'stretch':
                marginTop = 0;
                marginBottom = 0;
                break;
        }
    }


    return (
        <div
            style={{
                width: fontSize[0] + 'em',
                height: fontSize[1] + 'em',
                display: 'flex',
                flexDirection: flexDirection,
                position: 'relative',
            }}
        >
            {splitSequences.map((o, index) => {
                var temp;
                var newFontSize: [number, number] = (wrapGroup.includes(structureType) && index == 1)
                    ? [fontSize[0] * innerPercentSize[0], fontSize[1] * innerPercentSize[1]]
                    : [fontSize[0] * percentWidthList[index], fontSize[1] * percentHeightList[index]];

                if (o.length == 1 && !structureTypes.includes(o[0] as string)) {
                    // temp = <GlyphAdjustment structureId={o[0] as number} structureType={structureType} blankColour={'white'} fontSize={fontSize}/>;
                    // if (temp == <div></div>)
                        temp = <DrawStructure structureId={o[0] as number} fontSize={newFontSize} parentStructureType={structureType} index={index}/>; // receive glyph adjustment here
                } else {
                    temp = <PaintStructureTree input={o} fontSize={newFontSize} />;
                }
                // console.log(marginTop + '% ' + marginLeft + '% ' + marginRight + '% ' + marginBottom + '%');
                return (
                    <div
                        key={o[0] as number}
                        style={{
                            width: (wrapGroup.includes(structureType) && index == 1) ? (innerPercentSize[0] + 'em') : (percentWidthList[index] + 'em'),
                            height: (wrapGroup.includes(structureType) && index == 1) ? (innerPercentSize[1] + 'em') : (percentHeightList[index] + 'em'),
                            margin: (wrapGroup.includes(structureType) && index == 1) ?
                                (marginTop + 'em ' + marginRight + 'em ' + marginBottom + 'em ' + marginLeft + 'em') : 'unset',
                            // border: 'black solid 2px',
                            position: (wrapGroup.includes(structureType) && index == 1) ?
                                'absolute' :
                                (stackGroup.includes(structureType)
                                    ? 'absolute'
                                    : 'relative'),
                            display: 'flex',              // ADDED
                            alignItems: 'center',         // ADDED: vertical center
                            justifyContent: 'center',     // ADDED: horizontal center
                            boxSizing: 'border-box',      // ADDED
                            overflow: 'hidden',
                        }}
                    >
                        {temp}
                    </div>
                );
            })}
        </div>
    );
}

async function aggregateStructureTreeWidthHeight(input: (string | number)[]): Promise<[number, number]> {
    // the base case
    if (input.length == 1 && !structureTypes.includes(input[0] as string)) {
        return calcStructureWidthHeight(input[0] as number);
    }

    // var inputCopy = [...input.slice(1)];
    var structureTypeDescription = input[0] as string;
    var sizeList: [number, number][] = [];

    var splitSequences = splitStructureSequence(input);

    for (let i = 0; i < splitSequences.length; i++) {
        sizeList.push(await aggregateStructureTreeWidthHeight(splitSequences[i]));
    }
    // console.log("aggregateStructureTreeWidthHeight: (" + structureTypeDescription + ")" + aggregateStructureWidthHeight(structureTypeDescription, sizeList));
    return aggregateStructureWidthHeight(structureTypeDescription, sizeList);
}

function splitStructureSequence(input: (string | number)[]): (string | number)[][] {
    var inputSequence = [...input.slice(1)];
    var output: (string | number)[][] = [];
    var queue: (string | number)[] = [];
    var structureDescriptionQueue: string[] = [];
    var tempSequence: (string | number)[] = [];


    // condition to continue the loop
    while (inputSequence.length > 0) {
        let fetchedValue = inputSequence[0];

        queue.push(fetchedValue);
        tempSequence.push(fetchedValue);
        inputSequence = [...inputSequence.slice(1)];
        if (!structureTypes.includes(queue[queue.length - 1] as string)) {
            // if there are no longer structures
            if (structureDescriptionQueue.length == 0) {
                queue = [];
                output.push(tempSequence);
                tempSequence = [];
            } else
            while ((
                queue.length >= 2 &&
                !tripleGroup.includes(structureDescriptionQueue[structureDescriptionQueue.length - 1]) &&
                !structureTypes.includes(queue[queue.length - 2] as string) &&
                !structureTypes.includes(queue[queue.length - 1] as string)) ||
            ((
                queue.length >= 3 &&
                tripleGroup.includes(structureDescriptionQueue[structureDescriptionQueue.length - 1]) &&
                !structureTypes.includes(queue[queue.length - 3] as string) &&
                !structureTypes.includes(queue[queue.length - 2] as string) &&
                !structureTypes.includes(queue[queue.length - 1] as string))))
            {
                if (!tripleGroup.includes(structureDescriptionQueue[structureDescriptionQueue.length - 1])) {
                    queue.pop(); queue.pop(); queue.pop(); queue.push(0); // 0 is the placeholder
                    structureDescriptionQueue.pop();
                } else {
                    queue.pop(); queue.pop(); queue.pop(); queue.pop(); queue.push(0); // 0 is the placeholder
                    structureDescriptionQueue.pop();
                }
            }
            // when the queue is a complete structure sequence
            if (queue.length == 1) {
                queue = [];
                output.push(tempSequence);
                tempSequence = [];
            }
        } else {
            structureDescriptionQueue.push(fetchedValue as string);
        }
    }
    return output;
}