import {useEffect, useState} from 'react';
import EntityX from "Frontend/generated/com/liu/trachunom/entity/entity/EntityX";
import {
    EntityMapper,
    EntityService,
    ExampleService,
    PronunciationEvolutionService,
    PronunciationService,
    SourceService,
    StructureTypeService,
    RadicalService, StructureService
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
import StructureType from "Frontend/generated/com/liu/trachunom/entity/structure/StructureType";

export {
    HnomQngu as HnomQnguComponent,
    HnomStringByExampleId as HnomStringByExampleIdComponent,
    DrawEvolution as DrawEvolution,
    DrawStructure as DrawStructure,
    DrawPronunciationEvolution as DrawPronunciationEvolution,
    DrawMeaningEvolution as DrawMeaningEvolution,
    DrawEntityYear as DrawEntityYear,
    PaintStructure as PaintStructure,
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
                margin: '0px 0px 1em'
            }}
        >
            <p style={{fontSize: '0.4em', margin: '0px', lineHeight: '1em', position: 'absolute', top: '-1em'}}>
                {qnguString}
            </p>
            <p style={{fontSize: '1em', margin: '0px', lineHeight: '1em'}}>
                {hnomString}
            </p>
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
                <a
                    href={'/entity/' + evolution?.fromEntityId}
                    style={{
                        textDecoration: 'none',
                        color: 'black',
                    }}
                >
                    <div
                        style={{
                            fontFamily: 'serif',
                        }}
                    >
                        {(evolution?.fromEntity?.hnomString ?? '') + ' ' + (evolution?.fromEntity?.qnguString ?? '')}
                    </div>
                    <p>{evolution?.fromEntity?.explanationsString}</p>
                </a>
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

function DrawStructure({structure}: {structure : StructureDto | undefined }): JSX.Element {
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

    const recursiveValue = structureComponent ? <DrawStructure structure={structureComponent}/> : null;

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
};

function PaintStructure({ structure }: { structure: StructureDto | undefined }): JSX.Element {
    const structureTypeId = structure?.structureTypeId;
    const [structureType, setStructureType] = useState<StructureType | undefined>(undefined);
    const [structureComponents, setStructureComponents] = useState<StructureComponentDto[] | undefined>(undefined);
    const [structures, setStructures] = useState<StructureDto[] | undefined>(undefined);
    // const [doesExist, setDoesExist] = useState<boolean>(false);
    // const [doesExist2, setDoesExist2] = useState<boolean>(false);
    const [checkOfRadicalList, setCheckOfRadicalList] = useState<boolean[] | undefined>(undefined);


    useEffect(() => {
        if (structureTypeId == null) return;
        StructureTypeService.findById(structureTypeId)
            .then(o => setStructureType(o))
            .catch(error => console.error('Error finding structure type', error));
    }, [structureTypeId]);

    useEffect(() => {
        if (structure?.id == null) return;
        StructureEndpoint.getStructureComponentsByStructureId(structure.id)
            .then(data => data?.filter(o => o != undefined))
            .then(data =>
                data?.sort((o1, o2) =>
                    (o1?.position && o2?.position) ? (o1?.position - o2?.position) : 0))
            .then(data => setStructureComponents(data))
            .catch(error => console.error('Error finding structure components by structure id', error));
    }, [structure?.id]);


    useEffect(() => {
        if (!structureComponents || structureComponents.length === 0) {
            setStructures(undefined);
            return;
        }

        (async () => {
            const arraysOfArrays: (StructureDto[][]) = await Promise.all(
            structureComponents
                .map(async (o) => {
                    const idToFetch = o.structureComponentId ?? -1;
                    const raw = await StructureService.findById(idToFetch);
                    const s = await EntityMapper.toStructureDto(raw);
                    const output: StructureDto[] = [];
                    if (s) {
                        for (let i = 0; i < (o.quantity ?? 0); i++) {
                            output.push(s);
                        }
                    }
                    return output;
                })
            );

            const flat: StructureDto[] = arraysOfArrays.flat();
            setStructures(flat);

            const checkResults: boolean[] = await Promise.all(
                flat?.map(async (o) =>
                    RadicalService.existsByUnicode(o.character?.radical?.unicode?? -1)
                )
            );
            setCheckOfRadicalList(checkResults);

            // window.alert(structureComponents?.length + " " + structures?.length + " " + checkOfRadicalList?.length);
        })();

    }, [structureComponents]);

    // window.alert(!structure + " " + !structureType + " " + structureComponents?.length);
    if (!(structure && structureType && structureComponents?.length)) {
        // return <div>{structure?.characterString}</div>;
        return <div></div>;
    }

    const tempComponent = structureComponents?.at(0);
    const tempComponent2 = structureComponents?.length ?? 0 >= 1 ? structureComponents?.at(1) : null;

    // let gridTemplateColumns = structureComponents?.map(o => '1fr ').flat().at(0);;
    // let gridTemplateRows = gridTemplateColumns;
    let frList: number[] = [];
    let flexDirection = 'row';

    structureComponents.map(o => frList.push(o.quantity ?? 0));

    const length = checkOfRadicalList?.length;
    const comq1 = tempComponent?.quantity;
    const comq2 = tempComponent2?.quantity;
    const isRa1 = checkOfRadicalList?.at(0);
    const isRa2 = checkOfRadicalList?.at(1);

    switch (structureType?.description) {
        case '⿰':
            flexDirection = 'row';
            frList = (length === 2)
                ? ((comq1 === 2)
                    ? [1, 1]
                    : (
                        (comq1 === 1 && comq2 === 1)
                            ? (isRa1 && !isRa2
                                ? [1, 2]
                                : (!isRa1 && isRa2
                                    ? [2, 1]
                                    : (isRa1 && isRa2)
                                        ? [1, 2]
                                        : [1, 1]
                                    )
                            )
                            : [1]
                    )
                )
                : [1];
            break;
        //⿰⿲⿱⿳⿸⿺⿹⿽⿵⿷⿶⿼⿴⿻
        case '⿱':
            flexDirection = 'column';
            frList = (length === 2)
                ? ((comq1 === 2)
                        ? [1, 1]
                        : (
                            (comq1 === 1 && comq2 === 1)
                                ? (isRa1 && !isRa2
                                        ? [1, 2]
                                        : (!isRa1 && isRa2
                                                ? [2, 1]
                                                : [1, 1]
                                                // : ((isRa1 && isRa2)
                                                //     ? [1, 1]
                                                //     : [1, 3])
                                        )
                                )
                                : [1]
                        )
                )
                : [1];
            break;
    }
    return (
        <div
            key={structure?.id}
            style={{
                width: '100%',
                height: '100%',
                display: 'flex',
                flexDirection:  flexDirection,
            }}
        >
            {structures?.map((o, index) => (
                <div
                    key={index}
                    style={{
                        display: 'flex',
                        border: '2px solid ' +
                            (structureComponents?.at(index)?.classificationId == -1
                                ? 'blue'
                                : structureComponents?.at(index)?.classificationId == 0
                                    ? 'gray'
                                    : 'red'),
                        borderRadius: '3px',
                        boxSizing: 'border-box',
                        // gap: '10%',
                        margin: '1px',
                        flex: frList.at(index),
                    }}
                >
                    <PaintStructure structure={o} />
                </div>
            ))}
        </div>
    );
}

