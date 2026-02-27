import {useEffect, useState} from 'react';
import EntityX from "Frontend/generated/com/liu/trachunom/entity/EntityX";
import {
    EntityMapper,
    EntityService,
    ExampleService,
    PronunciationEvolutionService,
    PronunciationService
} from "Frontend/generated/endpoints";
import EntityEvolutionDto from "Frontend/generated/com/liu/trachunom/dto/EntityEvolutionDto";
import {
    getEntityEvolutionsByToEntityId,
    getGridTemplateColumnsByStructureId, getStructureDtoByStructureId
} from "Frontend/generated/EntityDetailEndpoint";
import type StructureDto from "Frontend/generated/com/liu/trachunom/dto/StructureDto";
import StructureComponentDto from "Frontend/generated/com/liu/trachunom/dto/StructureComponentDto";
import * as StructureEndpoint from "Frontend/generated/StructureEndpoint";
import PronunciationEvolution from "Frontend/generated/com/liu/trachunom/entity/PronunciationEvolution";
import Pronunciation from "Frontend/generated/com/liu/trachunom/entity/Pronunciation";
import PronunciationEvolutionDto from "Frontend/generated/com/liu/trachunom/dto/PronunciationEvolutionDto";
import Meaning from "Frontend/generated/com/liu/trachunom/entity/Meaning";

export {
    HnomQngu as HnomQnguComponent,
    HnomStringByExampleId as HnomStringByExampleIdComponent,
    DrawEvolution as DrawEvolution,
    DrawStructure as DrawStructure,
    DrawPronunciationEvolution as DrawPronunciationEvolution,
    DrawMeaningEvolution as DrawMeaningEvolution,
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
                color: entity?.id === markedId ? 'blue' : 'black',
            }}
        >
            <div>
                <p style={{fontSize: '0.8em', margin: '0px'}}>
                    {qnguString}
                </p>
            </div>
            <div>
                <p style={{fontSize: '1.5em', margin: '0px'}}>
                    {hnomString}
                </p>
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
        StructureEndpoint.getStructureComponents(structure.id)
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
                    <a style={{
                        textDecoration: "none",
                        color: "white",
                        fontWeight: "bold",
                        fontFamily: "sans-serif",
                        fontSize: "18px",
                    }}
                       href={"/search?query=" + component.structureComponentCharacterString + (component.quantity ? (component?.quantity > 1 ? "×" + component.quantity : "") : "")}>
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
};

function DrawPronunciationAncestors({pronunciationId}: {pronunciationId: number | undefined}): JSX.Element {
    const [ancestors, setAncestors] = useState<PronunciationEvolution[] | null>(null);
    useEffect(() => {
        if (!pronunciationId) {
            setAncestors(null);
            return;
        }

        let fetchedAncestors = PronunciationEvolutionService.findByToPronunciationId(pronunciationId ?? undefined)
            .then(data => data?.filter(evolution => evolution !== undefined))
            .then(data => setAncestors(data ?? null))
            .catch(error => console.error('Error fetching pronunciation evolutions:', error));
    }, [pronunciationId]);

    if (!ancestors || ancestors.length === 0) {
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
            {ancestors.map((ancestor, index) => (
                <div>
                    <DrawPronunciationAncestors pronunciationId={ancestor.fromPronunciation?.id}/>
                    <div key={index}>
                        {ancestor.fromPronunciation?.string} →
                    </div>
                </div>
            ))}
        </div>
    );
};

function DrawPronunciationDescendants({pronunciationId}: {pronunciationId: number | undefined}): JSX.Element {
    const [descendants, setDescendants] = useState<PronunciationEvolution[] | null>(null);
    useEffect(() => {
        if (!pronunciationId) {
            setDescendants(null);
            return;
        }

        PronunciationEvolutionService.findByFromPronunciationId(pronunciationId ?? undefined)
            .then(data => data?.filter(evolution => evolution !== undefined))
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
                <div>
                    <div key={index}>
                        → {descendant.toPronunciation?.string}
                    </div>
                    <DrawPronunciationDescendants pronunciationId={descendant.toPronunciation?.id}/>
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