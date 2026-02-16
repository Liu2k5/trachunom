import {ViewConfig} from '@vaadin/hilla-file-router/types.js';
import {useParams, useNavigate} from 'react-router';
import {useEffect, useState} from 'react';
import type EntityDetailDto from 'Frontend/generated/com/liu/trachunom/dto/EntityDetailDto';
import type StructureDto from "Frontend/generated/com/liu/trachunom/dto/StructureDto";
import {getEntityDetail} from 'Frontend/generated/EntityDetailEndpoint';
import {getStructureDtoByStructureId} from 'Frontend/generated/EntityDetailEndpoint';
import {getEntityEvolutionsByToEntityId} from 'Frontend/generated/EntityDetailEndpoint';
import {getGridTemplateColumnsByStructureId} from 'Frontend/generated/EntityDetailEndpoint';
import * as StructureEndpoint from 'Frontend/generated/StructureEndpoint';
import StructureComponentDto from "Frontend/generated/com/liu/trachunom/dto/StructureComponentDto";
import EntityEvolutionDto from "Frontend/generated/com/liu/trachunom/dto/EntityEvolutionDto";
import EntityDto from "Frontend/generated/com/liu/trachunom/dto/EntityDto";
import EntityX from "Frontend/generated/com/liu/trachunom/entity/EntityX";
import {EntityService} from "Frontend/generated/endpoints";
import {HnomQnguComponent} from 'Frontend/utils/entityUtils';

export const config: ViewConfig = {
    title: 'Chi Tiết Thực Thể',
    route: 'entity/:id',
};


export default function EntityDetailView() {
    const {id} = useParams<{ id: string }>();
    const navigate = useNavigate();
    const [entity, setEntity] = useState<EntityDetailDto | null | undefined>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // TODO: Fetch entity data from backend
        // Example: EntityService.getById(id)
        // Mock data for demonstration
        getEntityDetail(Number(id)).then((data) => {
            setEntity(data);
            setLoading(false);
        }).catch((error) => {
            console.error('Error fetching entity:', error);
            setLoading(false);
        });
    }, [id]);

    if (loading) {
        return (
            <div
                style={{
                    width: '100%',
                    minHeight: '100vh',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    background: '#f5f5f5',
                }}
            >
                <div style={{fontSize: '18px', color: '#666'}}>Đang tải...</div>
            </div>
        );
    }

    if (!entity) {
        return (
            <div
                style={{
                    width: '100%',
                    minHeight: '100vh',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    background: '#f5f5f5',
                }}
            >
                <div style={{textAlign: 'center'}}>
                    <h2 style={{color: '#666'}}>Không tìm thấy thực thể</h2>
                    <button
                        onClick={() => navigate('/search')}
                        style={{
                            marginTop: '20px',
                            padding: '10px 20px',
                            background: '#667eea',
                            color: 'white',
                            border: 'none',
                            borderRadius: '4px',
                            cursor: 'pointer',
                        }}
                    >
                        Quay lại tìm kiếm
                    </button>
                </div>
            </div>
        );
    }

    return (
        <div
            style={{
                width: '100%',
                minHeight: '100vh',
                background: '#f5f5f5',
                padding: '20px',
            }}
        >
            <div
                style={{
                    maxWidth: '1000px',
                    margin: '0 auto',
                }}
            >
                {/* Back button */}
                <button
                    onClick={() => navigate(-1)}
                    style={{
                        marginBottom: '20px',
                        padding: '8px 16px',
                        background: 'white',
                        border: '1px solid #ddd',
                        borderRadius: '4px',
                        cursor: 'pointer',
                        fontSize: '14px',
                    }}
                >
                    ← Quay lại
                </button>

                {/* Main content */}
                <div
                    style={{
                        background: 'white',
                        borderRadius: '8px',
                        boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
                        padding: '40px',
                    }}
                >
                    {/* Character display */}
                    <div
                        style={{
                            textAlign: 'center',
                            marginBottom: '40px',
                            paddingBottom: '30px',
                            borderBottom: '2px solid #f0f0f0',
                        }}
                    >
                        {!entity.compound ? (
                                <>
                                    <div
                                        style={{
                                            fontSize: '80px',
                                            fontWeight: 'bold',
                                            color: '#667eea',
                                            marginBottom: '20px',
                                            fontFamily: 'serif',
                                            display: 'flex',
                                            justifyContent: 'center',
                                            gap: '20px',
                                        }}
                                    >
                                        <HnomQnguComponent entityId={entity.id} markedId={0}/>
                                    </div>
                                    <div>
                                        <p>Unicode: U+{(entity.structure?.character?.characterString?.charCodeAt(0))?.toString(16).toUpperCase()}</p>
                                        <p>Bộ {entity.structure?.character?.radicalString}
                                            + {entity.structure?.character?.additionalStrokeNumber} nét,
                                            tổng {entity.structure?.character?.totalStrokeNumber}
                                        </p>
                                    </div>
                                </>
                            )
                            :
                            <>
                                <div
                                    style={{
                                        fontSize: '80px',
                                        fontWeight: 'bold',
                                        color: '#667eea',
                                        marginBottom: '20px',
                                        fontFamily: 'serif',
                                        display: 'flex',
                                        justifyContent: 'center',
                                        gap: '20px',
                                    }}
                                >
                                    {entity.compositionComponents?.map((component, index) => (
                                        <div key={index}>
                                            {component ? (
                                                <HnomQnguComponent entityId={component.id} markedId={0}/>
                                            ) : (
                                                <div style={{color: 'grey'}}>không có</div>
                                            )}
                                        </div>
                                    ))}
                                </div>
                                <div>
                                    <p>{entity.pronunciation?.pronunciationString}</p>
                                </div>
                            </>
                        }
                    </div>

                    {/* Meanings */}
                    {entity.meaning?.explanations && entity.meaning.explanations.length > 0 && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Ý nghĩa
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                {entity.meaning.explanations.map((explanation, idx) =>
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
                        </section>
                    )}

                    {/* Structure */}
                    {entity.structure && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Cấu tạo
                            </h2>
                            <div style={{width: '100%',}}>
                                <DrawStructure structure={entity.structure}/>
                            </div>
                        </section>
                    )}

                    {/* Evolution */}
                    {entity.evolutions && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Quá trình phát triển
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                <div>
                                    <p>{entity.hnomString} {entity.qnguString}</p>
                                    <p>{entity.explanationsString}</p>
                                </div>
                                {drawEvolution(entity.evolutions?.filter((evolution): evolution is EntityEvolutionDto => evolution !== undefined))}
                            </div>
                        </section>
                    )}

                    {/* Variances */}
                    {entity.variances && entity.variances.length > 0 && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Biến thể
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                {entity.variances.map(variance =>
                                    variance ? (
                                        <div
                                            key={variance.id}
                                            style={{
                                                marginBottom: '10px',
                                                fontSize: '18px',
                                            }}
                                        >
                                            <HnomQnguComponent entityId={variance.id} markedId={0}/>
                                        </div>
                                    ) : null
                                )}
                            </div>
                        </section>
                    )}

                    {/* Synonyms */}
                    {entity.synonyms && entity.synonyms.length > 0 && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Từ đồng nghĩa
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                {entity.synonyms?.map(synonym =>
                                    synonym ? (
                                        <div
                                            key={synonym.id}
                                            style={{
                                                marginBottom: '10px',
                                                fontSize: '18px',
                                            }}
                                        >
                                            <HnomQnguComponent entityId={synonym.id} markedId={0}/>
                                        </div>
                                    ) : null
                                )}
                            </div>
                        </section>
                    )}

                    {/* Examples */}
                    {entity.examples && entity.examples.length > 0 && (
                        <section>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Ví dụ
                            </h2>
                            {entity?.examples ? (
                                    entity.examples.map(example =>
                                        <>
                                            <div style={{
                                                display: 'flex',
                                                gap: '10px',
                                                marginBottom: '20px',
                                                alignItems: 'end',
                                            }}>
                                                <div
                                                    style={{
                                                        display: 'flex',
                                                        gap: '10px',
                                                        marginBottom: '10px',
                                                        fontFamily: 'sans-serif',
                                                    }}>
                                                    {
                                                        example?.exampleWords ? (
                                                            example.exampleWords.map(word => {
                                                                    if (word?.entity) {
                                                                        return (
                                                                            <HnomQnguComponent entityId={word.entity.id} markedId={entity.id ?? 0}/>
                                                                        );
                                                                    }
                                                                    return undefined;
                                                                }
                                                            )
                                                        ) : <div>khong co example</div>
                                                    }
                                                </div>
                                                <div>
                                                    <p
                                                        title={example ? example.sourceDescription : ''}
                                                        style={{
                                                            fontFamily: 'sans-serif',
                                                            fontStyle: 'italic',
                                                            color: '#666',
                                                            fontSize: '0.8em',
                                                        }}
                                                    >
                                                        {example ? example.sourceName : ''}
                                                    </p>
                                                </div>
                                            </div>
                                        </>
                                    )
                                )
                                : (
                                    <p style={{color: '#666', fontSize: '16px'}}>
                                        Không tìm thấy kết quả nào.
                                    </p>
                                )
                            }
                        </section>
                    )}
                </div>
            </div>
        </div>
    );
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
                       href={"/search?query=" + component.structureComponentCharacterString}>
                        {component.structureComponentCharacterString || ''}
                    </a>
                </div>
                <div>
                    {recursiveValue}
                </div>
            </div>
        </div>
    );
}

function drawEvolution(evolutions: EntityEvolutionDto[] | null | undefined): JSX.Element {
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
    const recursiveValue = evolution?.fromEntity ? drawEvolution(evolutions) : undefined;

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
                            // fontSize: '40px',
                            // fontWeight: 'bold',
                            // color: '#667eea',
                            // marginBottom: '10px',
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
