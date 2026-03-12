import {ViewConfig} from '@vaadin/hilla-file-router/types.js';
import {useEffect, useRef, useState, useMemo} from 'react';
import {AutoForm, AutoGrid} from "@vaadin/hilla-react-crud";
import {ComboBox, GridColumn} from "@vaadin/react-components";
import RadicalModel from 'Frontend/generated/com/liu/trachunom/entity/character/RadicalModel';
import RadicalDtoModel from "Frontend/generated/com/liu/trachunom/dto/RadicalDtoModel";
import RadicalDto from "Frontend/generated/com/liu/trachunom/dto/RadicalDto";
import Radical from 'Frontend/generated/com/liu/trachunom/entity/character/Radical';
import * as RadicalEndpoint from 'Frontend/generated/RadicalEndpoint';
import * as RadicalService from 'Frontend/generated/RadicalService';
import * as SourceEndpoint from 'Frontend/generated/SourceEndpoint';
import * as SourceService from 'Frontend/generated/SourceService';
import * as LanguageEndpoint from 'Frontend/generated/LanguageEndpoint';
import * as LanguageService from 'Frontend/generated/LanguageService';
import * as StyleEndpoint from 'Frontend/generated/StyleEndpoint';
import * as StyleService from 'Frontend/generated/StyleService';
import * as CharacterEndpoint from 'Frontend/generated/CharacterEndpoint';
import * as CharacterService from "Frontend/generated/CharacterService";
import * as TradSimpStandardEndpoint from 'Frontend/generated/TradSimpStandardEndpoint';
import * as TradSimpStandardService from "Frontend/generated/TradSimpStandardService";
import * as StructureEndpoint from 'Frontend/generated/StructureEndpoint';
import * as StructureService from 'Frontend/generated/StructureService';
import * as StructureComponentEndpoint from 'Frontend/generated/StructureComponentEndpoint';
import * as StructureComponentService from 'Frontend/generated/StructureComponentService';
import * as StructureClassificationService from 'Frontend/generated/StructureClassificationService';
import * as EntityService from 'Frontend/generated/EntityService';

import * as EntityMapper from 'Frontend/generated/EntityMapper';
import SourceDtoModel from "Frontend/generated/com/liu/trachunom/dto/SourceDtoModel";
import SourceModel from "Frontend/generated/com/liu/trachunom/entity/SourceModel";
import Source from "Frontend/generated/com/liu/trachunom/entity/Source";
import LanguageModel from "Frontend/generated/com/liu/trachunom/entity/LanguageModel";
import LanguageDtoModel from "Frontend/generated/com/liu/trachunom/dto/LanguageDtoModel";
import Language from "Frontend/generated/com/liu/trachunom/entity/Language";
import StyleModel from "Frontend/generated/com/liu/trachunom/entity/StyleModel";
import StyleDtoModel from "Frontend/generated/com/liu/trachunom/dto/StyleDtoModel";
import Style from "Frontend/generated/com/liu/trachunom/entity/Style";
import CharacterModel from "Frontend/generated/com/liu/trachunom/entity/character/CharacterXModel";
import CharacterDtoModel from "Frontend/generated/com/liu/trachunom/dto/CharacterDtoModel";
import Character from "Frontend/generated/com/liu/trachunom/entity/character/CharacterX";
import TradSimpStandardModel from "Frontend/generated/com/liu/trachunom/entity/character/TradSimpStandardModel";
import TradSimpStandardDtoModel from "Frontend/generated/com/liu/trachunom/dto/TradSimpStandardDtoModel";
import TradSimpStandard from "Frontend/generated/com/liu/trachunom/entity/character/TradSimpStandard";
import Structure from "Frontend/generated/com/liu/trachunom/entity/structure/Structure";
import StructureDto from "Frontend/generated/com/liu/trachunom/dto/StructureDto";
import StructureComponent from "Frontend/generated/com/liu/trachunom/entity/structure/StructureComponent";
import StructureComponentDto from "Frontend/generated/com/liu/trachunom/dto/StructureComponentDto";
import StructureComponentModel from "Frontend/generated/com/liu/trachunom/entity/structure/StructureComponentModel";
import StructureComponentDtoModel from "Frontend/generated/com/liu/trachunom/dto/StructureComponentDtoModel";
import StructureModel from "Frontend/generated/com/liu/trachunom/entity/structure/StructureModel";
import StructureDtoModel from "Frontend/generated/com/liu/trachunom/dto/StructureDtoModel";
import StructureClassification from "Frontend/generated/com/liu/trachunom/entity/structure/StructureClassification";
import StructureClassificationDto from "Frontend/generated/com/liu/trachunom/dto/StructureClassificationDto";
import PronunciationModel from "Frontend/generated/com/liu/trachunom/entity/pronunciation/PronunciationModel";
import PronunciationDtoModel from "Frontend/generated/com/liu/trachunom/dto/PronunciationDtoModel";
import Pronunciation from "Frontend/generated/com/liu/trachunom/entity/pronunciation/Pronunciation";
import * as PronunciationEndpoint from 'Frontend/generated/PronunciationEndpoint';
import * as PronunciationService from 'Frontend/generated/PronunciationService';
import {
    ExampleService,
    ExampleWordService,
    PronunciationEvolutionEndpoint,
    PronunciationEvolutionService,
    QuocNguEndpoint,
    QuocNguService
} from "Frontend/generated/endpoints";
import QuocNguModel from "Frontend/generated/com/liu/trachunom/entity/pronunciation/QuocNguModel";
import QuocNguDtoModel from "Frontend/generated/com/liu/trachunom/dto/QuocNguDtoModel";
import QuocNgu from "Frontend/generated/com/liu/trachunom/entity/pronunciation/QuocNgu";
import PronunciationEvolutionModel from "Frontend/generated/com/liu/trachunom/entity/pronunciation/PronunciationEvolutionModel";
import PronunciationEvolutionDtoModel from "Frontend/generated/com/liu/trachunom/dto/PronunciationEvolutionDtoModel";
import PronunciationEvolution from "Frontend/generated/com/liu/trachunom/entity/pronunciation/PronunciationEvolution";
import ExplanationModel from "Frontend/generated/com/liu/trachunom/entity/meaning/ExplanationModel";
import ExplanationDtoModel from "Frontend/generated/com/liu/trachunom/dto/ExplanationDtoModel";
import Explanation from "Frontend/generated/com/liu/trachunom/entity/meaning/Explanation";
import ExplanationDto from "Frontend/generated/com/liu/trachunom/dto/ExplanationDto";
import Meaning from "Frontend/generated/com/liu/trachunom/entity/meaning/Meaning";
import MeaningModel from "Frontend/generated/com/liu/trachunom/entity/meaning/MeaningModel";
import MeaningDtoModel from "Frontend/generated/com/liu/trachunom/dto/MeaningDtoModel";
import MeaningDto from "Frontend/generated/com/liu/trachunom/dto/MeaningDto";
import * as ExplanationService from 'Frontend/generated/ExplanationService';
import * as ExplanationEndpoint from 'Frontend/generated/ExplanationEndpoint';
import * as MeaningService from 'Frontend/generated/MeaningService';
import * as MeaningEndpoint from 'Frontend/generated/MeaningEndpoint';
import MeaningExplanation from "Frontend/generated/com/liu/trachunom/entity/meaning/MeaningExplanation";
import MeaningExplanationModel from "Frontend/generated/com/liu/trachunom/entity/meaning/MeaningExplanationModel";
import MeaningExplanationDto from "Frontend/generated/com/liu/trachunom/dto/MeaningExplanationDto";
import MeaningExplanationDtoModel from "Frontend/generated/com/liu/trachunom/dto/MeaningExplanationDtoModel";
import * as MeaningExplanationService from 'Frontend/generated/MeaningExplanationService';
import * as MeaningExplanationEndpoint from 'Frontend/generated/MeaningExplanationEndpoint';
import EntityX from "Frontend/generated/com/liu/trachunom/entity/entity/EntityX";
import EntityDto from "Frontend/generated/com/liu/trachunom/dto/EntityDto";
import EntityDtoModel from "Frontend/generated/com/liu/trachunom/dto/EntityDtoModel";
import * as EntityEndpoint from 'Frontend/generated/EntityEndpoint';
import EntityComposition from "Frontend/generated/com/liu/trachunom/entity/entity/EntityComposition";
import EntityCompositionModel from "Frontend/generated/com/liu/trachunom/entity/entity/EntityCompositionModel";
import EntityCompositionDto from "Frontend/generated/com/liu/trachunom/dto/EntityCompositionDto";
import EntityCompositionDtoModel from "Frontend/generated/com/liu/trachunom/dto/EntityCompositionDtoModel";
import * as EntityCompositionEndpoint from 'Frontend/generated/EntityCompositionEndpoint';
import EntityEvolution from "Frontend/generated/com/liu/trachunom/entity/entity/EntityEvolution";
import EntityEvolutionModel from "Frontend/generated/com/liu/trachunom/entity/entity/EntityEvolutionModel";
import EntityEvolutionDto from "Frontend/generated/com/liu/trachunom/dto/EntityEvolutionDto";
import EntityEvolutionDtoModel from "Frontend/generated/com/liu/trachunom/dto/EntityEvolutionDtoModel";
import * as EntityEvolutionEndpoint from 'Frontend/generated/EntityEvolutionEndpoint';
import EntityXModel from "Frontend/generated/com/liu/trachunom/entity/entity/EntityXModel";
import Example from "Frontend/generated/com/liu/trachunom/entity/example/Example";
import ExampleModel from "Frontend/generated/com/liu/trachunom/entity/example/ExampleModel";
import ExampleWord from "Frontend/generated/com/liu/trachunom/entity/example/ExampleWord";
import * as ExampleEndpoint from 'Frontend/generated/ExampleEndpoint';
import * as ExampleWordEndpoint from 'Frontend/generated/ExampleWordEndpoint';
import ExampleDtoModel from "Frontend/generated/com/liu/trachunom/dto/ExampleDtoModel";
import ExampleWordDto from "Frontend/generated/com/liu/trachunom/dto/ExampleWordDto";
import ExampleDto from "Frontend/generated/com/liu/trachunom/dto/ExampleDto";
import ExampleWordDtoModel from "Frontend/generated/com/liu/trachunom/dto/ExampleWordDtoModel";
import {HnomStringByExampleIdComponent} from "Frontend/utils/entityUtils";
import PronunciationDto from "Frontend/generated/com/liu/trachunom/dto/PronunciationDto";

export const config: ViewConfig = {
    menu: {order: 2, icon: 'la la-book'},
    title: 'Quản Lý Từ Điển',
    route: '/admin/dictionary-management',
    loginRequired: true,
};

interface TabProps {
    label: string,
    active: boolean,
    onClick: () => void,
    // key?: string
}

const Tab = ({label, active, onClick}: TabProps) => (
    <button
        onClick={onClick}
        style={{
            padding: '12px 24px',
            background: active ? '#667eea' : 'transparent',
            color: active ? 'white' : '#666',
            border: 'none',
            borderBottom: active ? '3px solid #667eea' : '3px solid transparent',
            cursor: 'pointer',
            fontSize: '16px',
            fontWeight: active ? '600' : '400',
            transition: 'all 0.3s ease',
        }}
    >
        {label}
    </button>
);

export default function DictionaryManagementView() {
    const [activeTab, setActiveTab] = useState('basics');

    const tabs = [
        {id: 'basics', label: 'Cơ bản'},
        {id: 'characters', label: 'Ký tự'},
        {id: 'structures', label: 'Cấu tạo'},
        {id: 'pronunciations', label: 'Phát âm'},
        {id: 'meanings', label: 'Ý nghĩa'},
        {id: 'entities', label: 'Thực thể'},
        {id: 'examples', label: 'Ví dụ'},
        {id: 'additional', label: 'Bổ sung'},
    ];


    return (
        <div
            style={{
                width: '100%',
                minHeight: '100vh',
                background: '#f5f5f5',
                fontFamily: 'sans-serif',
            }}
        >
            {/* Header */}
            <div
                style={{
                    background: 'white',
                    boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
                    marginBottom: '20px',
                }}
            >
                <div
                    style={{
                        maxWidth: '1400px',
                        margin: '0 auto',
                        padding: '20px',
                    }}
                >
                    <h1
                        style={{
                            color: '#667eea',
                            margin: '0 0 20px 0',
                            fontSize: '28px',
                        }}
                    >
                        Quản Lý Từ Điển Chữ Nôm
                    </h1>

                    {/* Tabs */}
                    <div
                        style={{
                            display: 'flex',
                            gap: '5px',
                            borderBottom: '1px solid #e0e0e0',
                        }}
                    >
                        {tabs.map((tab) => (
                            <Tab
                                key={tab.id}
                                label={tab.label}
                                active={activeTab === tab.id}
                                onClick={() => setActiveTab(tab.id)}
                            />
                        ))}
                    </div>
                </div>
            </div>

            {/* Content */}
            <div
                style={{
                    // maxWidth: '1400px',
                    margin: '0 auto',
                    // padding: '20px',
                }}
            >
                <div
                    style={{
                        background: 'white',
                        borderRadius: '8px',
                        boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
                        padding: '10px',
                        minHeight: '500px',
                    }}
                >
                    {activeTab === 'basics' && (
                        <div>
                            {/*<h2 style={{color: '#333', marginBottom: '20px'}}>*/}
                            {/*    Quản lý thông tin cơ bản*/}
                            {/*</h2>*/}
                            {/*<p style={{color: '#666'}}>*/}
                            {/*    Quản lý bộ thủ, ngôn ngữ, phong cách thư pháp, nguồn.*/}
                            {/*</p>*/}
                            <BasicsTabContent />
                        </div>
                    )}

                    {activeTab === 'characters' && (
                        <div>
                            {/*<h2 style={{color: '#333', marginBottom: '20px'}}>*/}
                            {/*    Quản lý ký tự*/}
                            {/*</h2>*/}
                            {/*<p style={{color: '#666'}}>*/}
                            {/*    Quản lý ký tự chữ Nôm và chuẩn phồn/giản thể*/}
                            {/*</p>*/}
                            <CharactersTabContent />
                        </div>
                    )}

                    {activeTab === 'structures' && (
                        <div>
                            {/*<h2 style={{color: '#333', marginBottom: '20px'}}>*/}
                            {/*    Quản lý cấu tạo*/}
                            {/*</h2>*/}
                            {/*<p style={{color: '#666'}}>*/}
                            {/*    Quản lý cấu tạo chữ Nôm và phân loại cấu tạo.*/}
                            {/*</p>*/}
                            <StructureTabContent />
                        </div>
                    )}

                    {activeTab === 'pronunciations' && (
                        <div>
                            {/*<h2 style={{color: '#333', marginBottom: '20px'}}>*/}
                            {/*    Quản lý phát âm*/}
                            {/*</h2>*/}
                            {/*<p style={{color: '#666'}}>*/}
                            {/*    Quản lý cách đọc, phân loại phát âm và thời kỳ phát âm.*/}
                            {/*</p>*/}
                            <PronunciationTabContent />
                        </div>
                    )}

                    {activeTab === 'meanings' && (
                        <MeaningTabContent />
                    )}

                    {activeTab === 'entities' && (
                        <EntityTabContent />
                    )}

                    {activeTab === 'examples' && (
                        <ExampleTabContent />
                    )}

                    {activeTab === 'additional' && (
                        <div>
                            <h2 style={{color: '#333', marginBottom: '20px'}}>
                                Thông tin bổ sung
                            </h2>
                            <p style={{color: '#666'}}>
                                Quản lý ví dụ, hình ảnh và các thông tin bổ sung khác.
                            </p>
                            {/* TODO: Add additional management features */}
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}

const BasicsTabContent = () => {
    const radicalGridRef = useRef<any>(null);
    const sourceGridRef = useRef<any>(null);
    const languageGridRef = useRef<any>(null);
    const styleGridRef = useRef<any>(null);

    const [selectedRadical, setSelectedRadical] = useState<Radical | undefined | null>(null);
    const [seletedRadicalDto, setSelectedRadicalDto] = useState<any>(null);
    useEffect(() => {
        EntityMapper.toRadicalDto(selectedRadical ?? undefined).then(dto => setSelectedRadicalDto(dto))
            .catch(error => console.error('Error mapping Radical to DTO:', error));
    }, [selectedRadical]);
    const deleteRadicalRenderer = ({item}: {item: Radical }) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await RadicalEndpoint.delete(item.id);
                    radicalGridRef.current?.refresh();
                    if (selectedRadical?.id === item.id) {
                        setSelectedRadical(null);
                    }
                } catch (error) {
                    console.error('Error deleting Radical:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };
    const [selectedSource, setSelectedSource] = useState<Source | undefined | null>(null);
    const [selectedSourceDto, setSelectedSourceDto] = useState<any>(null);
    useEffect(() => {
        EntityMapper.toSourceDto(selectedSource ?? undefined).then(dto => setSelectedSourceDto(dto))
            .catch(error => console.error('Error mapping Source to DTO:', error));
    }, [selectedSource]);
    const deleteSourceRenderer = ({item}: {item: Source }) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await SourceEndpoint.delete(item.id);
                    sourceGridRef.current?.refresh();
                    if (selectedSource?.id === item.id) {
                        setSelectedSource(null);
                    }
                } catch (error) {
                    console.error('Error deleting Source:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };
    const [selectedLanguage, setSelectedLanguage] = useState<Language | undefined | null>(null);
    const [selectedLanguageDto, setSelectedLanguageDto] = useState<any>(null);
    useEffect(() => {
        EntityMapper.toLanguageDto(selectedLanguage ?? undefined).then(dto => setSelectedLanguageDto(dto))
            .catch(error => console.error('Error mapping Language to DTO:', error));
    })
    const deleteLanguageRenderer = ({item} : {item: Language}) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await LanguageEndpoint.delete(item.id);
                    languageGridRef.current?.refresh();
                    if (selectedLanguage?.id === item.id) {
                        setSelectedLanguage(null);
                    }
                } catch (error) {
                    console.error('Error deleting Language:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    }
    const [selectedStyle, setSelectedStyle] = useState<Style | undefined | null>(null);
    const [selectedStyleDto, setSelectedStyleDto] = useState<any>(null);
    useEffect(() => {
        EntityMapper.toStyleDto(selectedStyle ?? undefined).then(dto => setSelectedStyleDto(dto))
            .catch(error => console.error('Error mapping Style to DTO:', error));
    })
    const deleteStyleRenderer = ({item}: {item: any}) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await StyleEndpoint.delete(item.id);
                    styleGridRef.current?.refresh();
                    if (selectedStyle?.id === item.id) {
                        setSelectedStyle(null);
                    }
                } catch (error) {
                    console.error('Error deleting Style:', error);
                }
            }
        }
        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        )
    }

    return (
        <div
        style={{
            display: 'grid',
            gridTemplateColumns: '3fr 1fr 3fr 1fr',
            gridGap: '20px',
        }}
        >
            {/* TODO: Add management grids for Language, Style, Source, etc. */}
            <AutoGrid service={RadicalService}
                      ref={radicalGridRef}
                      model={RadicalModel}
                      selectedItems={[selectedRadical]}
                      onActiveItemChanged={i => setSelectedRadical(i.detail.value)}
                      columnOptions={{
                          radicalUnicode: {header: 'U bộ thủ'},
                          unicode: {header: 'Unicode'},
                          strokeNumber: {header: 'Số nét'},
                      }}
                      customColumns={[
                          <GridColumn header="Xóa" renderer={deleteRadicalRenderer} />,
                      ]}
                      hiddenColumns={['string']}
            />

            <AutoForm service={RadicalEndpoint}
                      model={RadicalDtoModel}
                      item={seletedRadicalDto}
                      hiddenFields={['string']}
                      onSubmitSuccess={() => {
                          setSelectedRadicalDto(null);
                          radicalGridRef.current?.refresh();
                      }}
                      onSubmitError={error => window.alert('Lỗi khi lưu bộ thủ: ' + error.error.message)}
                        fieldOptions={{
                            radicalUnicode: {label: 'Unicode bộ thủ'},
                            unicode: {label: 'Unicode'},
                            strokeNumber: {label: 'Số nét'},}
                        }
            />
            
            <AutoGrid service={SourceService}
                      ref={sourceGridRef}
                      model={SourceModel}
                      selectedItems={[selectedSource]}
                      onActiveItemChanged={i => setSelectedSource(i.detail.value)}
                      columnOptions={{
                          name: {header: 'Tên nguồn'},
                          description: {header: 'Mô tả'},
                      }}
                      customColumns={[
                            <GridColumn header="Xóa" renderer={deleteSourceRenderer} />
                      ]}
            />

            <AutoForm service={SourceEndpoint}
                      model={SourceDtoModel}
                      item={selectedSourceDto}
                      onSubmitSuccess={() => {
                          setSelectedSourceDto(null);
                          sourceGridRef.current?.refresh();
                      }}
                      onSubmitError={error => window.alert('Lỗi khi lưu nguồn: ' + error.error.message)}
                      fieldOptions={{
                          name: {label: 'Tên nguồn'},
                          description: {label: 'Mô tả'},
                      }}
            />

            <AutoGrid service={LanguageService}
                      ref={languageGridRef}
                      model={LanguageModel}
                      selectedItems={[selectedLanguage]}
                      onActiveItemChanged={i => setSelectedLanguage(i.detail.value)}
                      columnOptions={{
                          abbreviation: {header: 'Viết tắt'},
                      }}
                      customColumns={[
                          <GridColumn header="Xóa" renderer={deleteLanguageRenderer} />
                      ]}
            />

            <AutoForm service={LanguageEndpoint}
                      model={LanguageDtoModel}
                      item={selectedLanguageDto}
                      onSubmitSuccess={() => {
                          setSelectedLanguageDto(null);
                          languageGridRef.current?.refresh();
                      }}
                      onSubmitError={error => window.alert('Lỗi khi lưu ngôn ngữ: ' + error.error.message)}
                      fieldOptions={{
                          abbreviation: {label: 'Viết tắt'},
                      }}
            />

            <AutoGrid service={StyleService}
                      ref={styleGridRef}
                      model={StyleModel}
                      selectedItems={[selectedStyle]}
                      onActiveItemChanged={i => setSelectedStyle(i.detail.value)}
                      columnOptions={{
                          description: {header: 'Mô tả'},
                      }}
                      customColumns={[
                          <GridColumn header="Xóa" renderer={deleteStyleRenderer} />
                      ]}
            />

            <AutoForm service={StyleEndpoint}
                      model={StyleDtoModel}
                      item={selectedStyleDto}
                      onSubmitSuccess={() => {
                          setSelectedStyleDto(null);
                          styleGridRef.current?.refresh();
                      }}
                      onSubmitError={error => window.alert('Lỗi khi lưu phong cách: ' + error.error.message)}
                      fieldOptions={{
                          description: {label: 'Mô tả'},
                      }}
            />
        </div>
    );
};

const CharactersTabContent = () => {
    const characterGridRef = useRef<any>(null);
    const tradSimpStandardGridRef = useRef<any>(null);

    const [selectedCharacter, setSelectedCharacter] = useState<Character | undefined | null>(null);
    const [seletedCharacterDto, setSelectedCharacterDto] = useState<any>(null);
    const [selectedTradSimpStandard, setSelectedTradSimpStandard] = useState<TradSimpStandard | undefined | null>(null);
    const [seletedTradSimpStandardDto, setSelectedTradSimpStandardDto] = useState<any>(null);

    useEffect(() => {
        EntityMapper.toCharacterDto(selectedCharacter ?? undefined).then(dto => setSelectedCharacterDto(dto))
            .catch(error => console.error('Error mapping Character to DTO:', error));
    }, [selectedCharacter]);
    useEffect(() => {
        EntityMapper.toTradSimpStandardDto(selectedTradSimpStandard ?? undefined).then(dto => setSelectedTradSimpStandardDto(dto))
            .catch(error => console.error('Error mapping Standard to DTO:', error));
    }, [selectedTradSimpStandard]);

    const deleteCharacterRenderer = ({item}: {item: Character }) => {
        const handleDelete = async () => {
            if (item.unicode) {
                try {
                    await CharacterEndpoint.delete(item.unicode);
                    characterGridRef.current?.refresh();
                    if (selectedCharacter?.unicode === item.unicode) {
                        setSelectedCharacter(null);
                    }
                } catch (error) {
                    console.error('Error deleting Character:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const deleteTradSimpStandardRenderer = ({item}: {item: TradSimpStandard }) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await TradSimpStandardEndpoint.delete(item.id);
                    tradSimpStandardGridRef.current?.refresh();
                    if (selectedTradSimpStandard?.id === item.id) {
                        setSelectedTradSimpStandard(null);
                    }
                } catch (error) {
                    console.error('Error deleting TradSimpStandard:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const [radicals, setRadicals] = useState<Radical[] | undefined | null>(null);
    useEffect(() => {
        RadicalService.findAll().then(EntityMapper.toRadicalDtoList).then(list => {
            const tempList : RadicalDto[] = [];
            list?.forEach(radical => {
                if (radical) tempList.push(radical);
            });
            setRadicals(tempList);
        })
            .catch(error => console.error('Error fetching radicals:', error));
    }, []);

    return (
        <div
            style={{
                display: 'grid',
                gridTemplateColumns: '1fr 1fr',
                gridGap: '20px',
            }}
        >
            <div>
                <AutoGrid
                    service={CharacterService}
                    ref={characterGridRef}
                    model={CharacterModel}
                    selectedItems={[selectedCharacter]}
                    onActiveItemChanged={i => setSelectedCharacter(i.detail.value)}
                    columnOptions={{
                        unicode: {header: 'Unicode'},
                        additionalStrokeNumber: {header: 'Số nét phụ'},
                        totalStrokeNumber: {header: 'Tổng số nét'},
                        string: {
                            header: 'Ký tự',
                            sortable: false,
                            filterable: false
                        },
                        radical: {
                            header: 'Bộ thủ',
                            path: 'radical.string',
                        }
                    }}
                    // visibleColumns={['unicode', 'radical', 'additionalStrokeNumber', 'totalStrokeNumber',]}
                    hiddenColumns={['radicalString',]}
                    customColumns={[
                        <GridColumn path="delete" header="Xóa" renderer={deleteCharacterRenderer} />,
                    ]}
                />

                <AutoForm
                    service={CharacterEndpoint}
                    model={CharacterDtoModel}
                    item={seletedCharacterDto}
                    onSubmitSuccess={() => {
                        setSelectedCharacterDto(null);
                        characterGridRef.current?.refresh();
                    }}
                    onSubmitError={error => window.alert('Lỗi khi lưu ký tự: ' + error.error.message)}
                    hiddenFields={['radicalString',]}
                    fieldOptions={{
                        unicode: {label: 'Unicode'},
                        radical: {
                            label: 'Bộ thủ',
                            renderer:
                                ({field}) => {
                                    return (
                                        <ComboBox
                                            label='Bộ thủ'
                                            itemLabelPath='string'
                                            itemValuePath='id'
                                            items={radicals || []}
                                            // selectedItem={selectedCharacter?.radical}
                                            {...field}
                                        />
                                    );
                                }},
                        additionalStrokeNumber: {label: 'Số nét phụ'},
                        totalStrokeNumber: {label: 'Tổng số nét'},
                    }}
                />
            </div>
            <div>
                <AutoGrid
                    service={TradSimpStandardService}
                    ref={tradSimpStandardGridRef}
                    model={TradSimpStandardModel}
                    selectedItems={[selectedTradSimpStandard]}
                    onActiveItemChanged={i => setSelectedTradSimpStandard(i.detail.value)}
                    columnOptions={{
                        traditionalCharacter: {
                            header: 'Chữ phồn thể',
                            path: 'traditionalCharacter.string',
                        },
                        simplifiedCharacter: {
                            header: 'Chữ giản thể',
                            path: 'simplifiedCharacter.string',
                        },
                    }}
                    // visibleColumns={['traditionalCharacter', 'simplifiedCharacter',]}
                    hiddenColumns={['id',]}
                    customColumns={[
                        <GridColumn header={'Xóa'} renderer={deleteTradSimpStandardRenderer} />,
                    ]}
                />

                <AutoForm
                    service={TradSimpStandardEndpoint}
                    model={TradSimpStandardDtoModel}
                    item={seletedTradSimpStandardDto}
                    onSubmitSuccess={() => {
                        setSelectedTradSimpStandardDto(null);
                        tradSimpStandardGridRef.current?.refresh();
                    }}
                    onSubmitError={error => window.alert('Lỗi khi lưu chuẩn phồn/giản thể: ' + error.error.message)}
                    hiddenFields={['traditionalCharacter', 'simplifiedCharacter']}
                />
            </div>
        </div>
    );
};

const StructureTabContent = () => {
    const structureGridRef = useRef<any>(null);
    const structureComponentGridRef = useRef<any>(null);

    const [selectedStructure, setSelectedStructure] = useState<Structure | undefined | null>(null);
    const [selectedStructureDto, setSelectedStructureDto] = useState<StructureDto | undefined | null>(null);
    const [selectedStructureComponent, setSelectedStructureComponent] = useState<StructureComponent | undefined | null>(null);
    const [selectedStructureComponentDto, setSelectedStructureComponentDto] = useState<StructureComponentDto | undefined | null>(null);
    const [refreshComponentsTrigger, setRefreshComponentsTrigger] = useState(0);

    const [refreshTrigger, setRefreshTrigger] = useState(false);

    useEffect(() => {
        EntityMapper.toStructureDto(selectedStructure ?? undefined).then(dto => setSelectedStructureDto(dto))
            .catch(error => console.error('Error mapping Structure to DTO:', error));
    }, [selectedStructure, refreshTrigger]);

    // lam moi form cua entity component khi chuyen sang structure khac, tranh viec form con du lieu cu khi chuyen sang structure moi
    useEffect(() => {
        setSelectedStructureComponent(null);
    }, [selectedStructure]);

    useEffect(() => {
        if (selectedStructureComponent) {
            // Editing existing component
            EntityMapper.toStructureComponentDto(selectedStructureComponent)
                .then(dto => {
                    setSelectedStructureComponentDto(dto);
                })
                .catch(error => console.error('Error mapping StructureComponent to DTO:', error));
        } else if (selectedStructure?.id) {
            // Creating new component - set structureId using the new method
            EntityMapper.getNewStructureComponentDtoWithStructureId(selectedStructure.id)
                .then(newDto => {
                    setSelectedStructureComponentDto(newDto);
                })
                .catch(error => console.error('Error creating new StructureComponentDto:', error));
        } else {
            // No structure selected, clear the form
            setSelectedStructureComponentDto(null);
        }
    }, [selectedStructureComponent, selectedStructure]);

    const [structureClassifications, setStructureClassifications] = useState<StructureClassification[] | undefined | null>(null);
    useEffect(() => {
        StructureClassificationService.findAll().then(EntityMapper.toStructureClassificationDtoList).then(list => {
            const tempList : StructureClassificationDto[] = [];
            list?.forEach(classification => {
                if (classification) tempList.push(classification);
            });
            setStructureClassifications(tempList);
        })
            .catch(error => console.error('Error fetching structure classifications:', error));
    }, []);

    const [structures, setStructures] = useState<Structure[] | undefined | null>(null);
    const [structureDtos, setStructureDtos] = useState<StructureDto[] | undefined | null>(null);

    useEffect(() => {
        StructureService.findAll()
            .then((list: (Structure | undefined)[] | undefined) => {
                const tempList: Structure[] = [];
                list?.forEach((structure) => {
                    if (structure) tempList.push(structure);
                });
                setStructures(tempList);
            })
            .catch((error) => console.error('Error fetching structures:', error));
    }, [refreshTrigger]);

    useEffect(() => {
        if (structures) {
            EntityMapper.toStructureDtoList(structures)
                .then(dtos => dtos?.filter(dto => dto !== null))
                .then(dtos => {
                    const tempList: StructureDto[] = [];
                    dtos?.forEach((dto) => {
                        if (dto) tempList.push(dto);
                    });
                    return tempList;
                })
                .then(dtos => setStructureDtos(dtos));
        }
    }, [structures]);

    const [structureComponents, setStructureComponents] = useState<StructureComponent[] | undefined | null>(null);
    useEffect(() => {
        if (!selectedStructure) {
            setStructureComponents(null);
            return;
        }
        StructureComponentService.findByStructure(selectedStructure)
            .then(list => {
                const tempList : StructureComponent[] = [];
                list?.forEach(component => {
                    if (component) tempList.push(component);
                });
                setStructureComponents(tempList);
            })
            .catch(error => console.error('Error fetching structure components:', error));
    }, [selectedStructure, refreshComponentsTrigger]);

    const deleteStructureRenderer = ({item}: {item: Structure }) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await StructureEndpoint.delete(item.id);
                    structureGridRef.current?.refresh();
                    if (selectedStructure?.id === item.id) {
                        setSelectedStructure(null);
                    }
                    setRefreshTrigger(!refreshTrigger);
                } catch (error) {
                    console.error('Error deleting Structure:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const deleteStructureComponentRenderer = ({item}: {item: StructureComponent }) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await StructureComponentEndpoint.delete(item.id);
                    structureComponentGridRef.current?.refresh();
                    if (selectedStructureComponent?.id === item.id) {
                        setSelectedStructureComponent(null);
                    }
                } catch (error) {
                    console.error('Error deleting StructureComponent:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    return (
        <div
            style={{
                display: 'grid',
                gridTemplateColumns: '1fr 2fr',
                gridGap: '20px',
            }}
        >
            <div>
                <AutoGrid
                    service={StructureService}
                    ref={structureGridRef}
                    model={StructureModel}
                    selectedItems={[selectedStructure]}
                    onActiveItemChanged={i => setSelectedStructure(i.detail.value)}
                    columnOptions={{
                        id: {header: 'ID'},
                        character: {
                            header: 'Ký tự',
                            path: 'character.string',
                        },
                    }}
                    customColumns={[
                        <GridColumn header="Xóa" renderer={deleteStructureRenderer} />,
                    ]}
                    hiddenColumns={['characterString']}
                />

                <AutoForm
                    service={StructureEndpoint}
                    model={StructureDtoModel}
                    item={selectedStructureDto}
                    onSubmitSuccess={() => {
                        setSelectedStructureDto(null);
                        structureGridRef.current?.refresh();
                        setRefreshTrigger(!refreshTrigger);
                    }}
                    onSubmitError={error => window.alert('Lỗi khi lưu cấu tạo: ' + error.error.message)}
                    hiddenFields={['character', 'characterWithPronunciationsString']}
                />
            </div>

            <div>
                <AutoGrid
                    service={StructureComponentService}
                    ref={structureComponentGridRef}
                    model={StructureComponentModel}
                    selectedItems={[selectedStructureComponent]}
                    onActiveItemChanged={i => setSelectedStructureComponent(i.detail.value)}
                    items={structureComponents || []}
                    columnOptions={{
                        id: {
                            header: 'ID',
                            path: 'id.structureComponentId',
                        },
                        structureComponent: {
                            header: 'Thành phần',
                            path: 'structureComponent.characterString',
                        },
                        structureClassification: {
                            header: 'Phân loại',
                            path: 'structureClassification.description',
                        },
                        quantity: {header: 'Số lượng', filterable: false},
                    }}
                    customColumns={[
                        <GridColumn header="Xóa" renderer={deleteStructureComponentRenderer} />,
                    ]}
                    hiddenColumns={['structure']}
                />

                <AutoForm
                    service={StructureComponentEndpoint}
                    model={StructureComponentDtoModel}
                    item={selectedStructureComponentDto}
                    onSubmitSuccess={() => {
                        // setSelectedStructureComponentDto(null);
                        // setSelectedStructureComponent(null);

                        setRefreshComponentsTrigger(prev => prev + 1);
                        structureComponentGridRef.current?.refresh();
                    }}
                    onSubmitError={error => window.alert('Lỗi khi lưu thành phần cấu tạo: ' + error.error.message)}
                    fieldOptions={{
                        structureId: {
                            label: 'ID Cấu tạo',
                            readonly: true,
                        },
                        structureComponentId: {
                            label: 'Thành phần',
                            renderer: ({field}) => {
                                return (
                                    <ComboBox
                                        {...field}
                                        label="Thành phần"
                                        itemLabelPath="characterWithPronunciationsString"
                                        itemValuePath="id"
                                        items={structureDtos || []}
                                        renderer={item =>
                                            <span>{item.item.id + ' - ' + item.item.characterWithPronunciationsString}</span>}
                                    />
                                );
                            },
                        },
                        classificationId: {
                            label: 'Phân loại',
                            renderer: ({field}) => {
                                return (
                                    <ComboBox
                                        {...field}
                                        label="Phân loại"
                                        itemLabelPath="description"
                                        itemValuePath="id"
                                        items={structureClassifications || []}
                                    />
                                );
                            },
                        },
                        quantity: {label: 'Số lượng'},
                    }}
                    hiddenFields={selectedStructureComponent ?
                        ['id', 'structureId', 'structureCharacterString', 'structureComponentCharacterString',
                            'classificationDescription', 'structure', 'structureComponent', 'structureClassification'] :
                        ['id', 'structureCharacterString', 'structureComponentCharacterString',
                            'classificationDescription', 'structure', 'structureComponent', 'structureClassification']}
                />

            </div>
        </div>
    );
};

const PronunciationTabContent = () => {
    const quocNguGridRef = useRef<any>(null);
    const pronunciationGridRef = useRef<any>(null);
    const pronunciationEvolutionGridRef = useRef<any>(null);

    const [selectedQuocNgu, setSelectedQuocNgu] = useState<QuocNgu | undefined | null>(null);
    const [selectedQuocNguDto, setSelectedQuocNguDto] = useState<any>(null);
    const [selectedPronunciation, setSelectedPronunciation] = useState<Pronunciation | undefined | null>(null);
    const [selectedPronunciationDto, setSelectedPronunciationDto] = useState<any>(null);
    const [selectedPronunciationEvolution, setSelectedPronunciationEvolution] = useState<PronunciationEvolution | undefined | null>(null);
    const [selectedPronunciationEvolutionDto, setSelectedPronunciationEvolutionDto] = useState<any>(null);

    useEffect(() => {
        EntityMapper.toQuocNguDto(selectedQuocNgu ?? undefined).then(dto => setSelectedQuocNguDto(dto))
            .catch(error => console.error('Error mapping QuocNgu to DTO:', error));
    }, [selectedQuocNgu]);

    useEffect(() => {
        EntityMapper.toPronunciationDto(selectedPronunciation ?? undefined).then(dto => setSelectedPronunciationDto(dto))
            .catch(error => console.error('Error mapping Pronunciation to DTO:', error));
    }, [selectedPronunciation]);

    useEffect(() => {
        EntityMapper.toPronunciationEvolutionDto(selectedPronunciationEvolution ?? undefined).then(dto => setSelectedPronunciationEvolutionDto(dto))
            .catch(error => console.error('Error mapping PronunciationEvolution to DTO:', error));
    }, [selectedPronunciationEvolution]);

    const deleteQuocNguRenderer = ({item}: {item: QuocNgu }) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await QuocNguEndpoint.delete(item.id);
                    quocNguGridRef.current?.refresh();
                    if (selectedQuocNgu?.id === item.id) {
                        setSelectedQuocNgu(null);
                    }
                } catch (error) {
                    console.error('Error deleting QuocNgu:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const deletePronunciationRenderer = ({item}: {item: Pronunciation }) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await PronunciationEndpoint.delete(item.id);
                    pronunciationGridRef.current?.refresh();
                    if (selectedPronunciation?.id === item.id) {
                        setSelectedPronunciation(null);
                    }
                } catch (error) {
                    console.error('Error deleting Pronunciation:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const deletePronunciationEvolutionRenderer = ({item}: {item: PronunciationEvolution }) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await PronunciationEvolutionEndpoint.delete(item.id);
                    pronunciationEvolutionGridRef.current?.refresh();
                    if (selectedPronunciationEvolution?.id === item.id) {
                        setSelectedPronunciationEvolution(null);
                    }
                } catch (error) {
                    console.error('Error deleting PronunciationEvolution:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const [quocNgus, setQuocNgus] = useState<QuocNgu[] | undefined | null>(null);
    const refreshQuocNgusTrigger = () => {
        QuocNguService.findAll()
            .then((list: (QuocNgu | undefined)[] | undefined) => {
                const tempList: QuocNgu[] = [];
                list?.forEach((quocNgu) => {
                    if (quocNgu) tempList.push(quocNgu);
                });
                setQuocNgus(tempList);
            })
            .catch(error => console.error('Error fetching quoc ngus:', error));
    };
    useEffect(refreshQuocNgusTrigger, []);

    const [pronunciations, setPronunciations] = useState<Pronunciation[] | undefined | null>(null);
    const [pronunciationDtos, setPronunciationDtos] = useState<PronunciationDto[] | undefined | null>(null);

    const pronunciationsTrigger = () => {
        PronunciationService.findAll()
            .then((list: (Pronunciation | undefined)[] | undefined) => {
                const tempList: Pronunciation[] = [];
                list?.forEach((pronunciation) => {
                    if (pronunciation) tempList.push(pronunciation);
                });
                setPronunciations(tempList);
            })
            .catch((error) => console.error('Error fetching pronunciations:', error));
    };

    useEffect(pronunciationsTrigger, []);
    useEffect(() => {
        if (pronunciations) {
            EntityMapper.toPronunciationDtoList(pronunciations)
                .then(data => data?.filter(dto => dto !== undefined))
                .then(dtos => setPronunciationDtos(dtos));
        }
    }, [pronunciations]);

    return (
        <div
            style={{
                display: 'grid',
                gridTemplateColumns: '1fr 1fr 2fr',
                gridGap: '20px',
            }}
        >
            <div>
                <AutoGrid
                    service={QuocNguService}
                    ref={quocNguGridRef}
                    model={QuocNguModel}
                    selectedItems={[selectedQuocNgu]}
                    onActiveItemChanged={i => setSelectedQuocNgu(i.detail.value)}
                    columnOptions={{
                        id: {
                            header: 'ID',
                        },
                        description: {header: 'Hợp lệ'},
                    }}
                    customColumns={[
                        <GridColumn header="Xóa" renderer={deleteQuocNguRenderer} />,
                    ]}
                />
                <AutoForm
                    service={QuocNguEndpoint}
                    model={QuocNguDtoModel}
                    item={selectedQuocNguDto}
                    onSubmitSuccess={() => {
                        setSelectedQuocNguDto(null);
                        quocNguGridRef.current?.refresh();
                        refreshQuocNgusTrigger();
                    }}
                    onSubmitError={error => window.alert('Lỗi khi lưu quốc ngữ: ' + error.error.message)}
                    fieldOptions={{
                        description: {label: 'Quốc ngữ'},
                    }}
                />
            </div>
            <div>
                <AutoGrid
                    service={PronunciationService}
                    ref={pronunciationGridRef}
                    model={PronunciationModel}
                    selectedItems={[selectedPronunciation]}
                    onActiveItemChanged={i => setSelectedPronunciation(i.detail.value)}
                    columnOptions={{
                        id: {
                            header: 'ID',
                        },
                        quocNgu: {
                            header: 'Phát âm',
                            path: 'string',
                        },
                    }}
                    customColumns={[
                        <GridColumn header="Xóa" renderer={deletePronunciationRenderer} />,
                    ]}
                    hiddenColumns={['string']}
                />
                <AutoForm
                    service={PronunciationEndpoint}
                    model={PronunciationDtoModel}
                    item={selectedPronunciationDto}
                    onSubmitSuccess={() => {
                        setSelectedPronunciationDto(null);
                        pronunciationGridRef.current?.refresh();
                        pronunciationsTrigger();
                    }}
                    onSubmitError={error => window.alert('Lỗi khi lưu phát âm: ' + error.error.message)}
                    fieldOptions={{
                        quocNguId: {
                            label: 'Quốc ngữ',
                            renderer: ({field}) => {
                                return (
                                    <ComboBox
                                        {...field}
                                        label="Quốc ngữ"
                                        itemLabelPath="description"
                                        itemValuePath="id"
                                        items={quocNgus || []}
                                        renderer={item =>
                                            <div>{item.item.id + ' - ' + item.item.description}</div>}
                                    />
                                );
                            },
                        },
                    }}
                    hiddenFields={['pronunciationString', 'characterWithPronunciationsString']}
                />
            </div>
            <div>
                <AutoGrid
                    service={PronunciationEvolutionService}
                    ref={pronunciationEvolutionGridRef}
                    model={PronunciationEvolutionModel}
                    selectedItems={[selectedPronunciationEvolution]}
                    onActiveItemChanged={i => setSelectedPronunciationEvolution(i.detail.value)}
                    columnOptions={{
                        id: {
                            header: 'ID',
                            path: 'id.string',
                        },
                        fromPronunciation: {
                            header: 'Gốc',
                            path: 'fromPronunciation.string',
                        },
                        toPronunciation: {
                            header: 'Đích',
                            path: 'toPronunciation.string',
                        }
                    }}
                    customColumns={[
                        <GridColumn header="Xóa" renderer={deletePronunciationEvolutionRenderer} />,
                    ]}
                />
                <AutoForm
                    service={PronunciationEvolutionEndpoint}
                    model={PronunciationEvolutionDtoModel}
                    item={selectedPronunciationEvolutionDto}
                    onSubmitSuccess={() => {
                        setSelectedPronunciationEvolution(null);
                        pronunciationEvolutionGridRef.current?.refresh();
                    }}
                    onSubmitError={error => window.alert('Lỗi lưu phát triển âm đọc: ' + error.error.message)}
                    fieldOptions={{
                        fromPronunciationId: {
                            label: 'Phát âm gốc',
                            renderer: ({field}) => {
                                return (
                                    <ComboBox
                                        {...field}
                                        label="Phát âm gốc"
                                        itemLabelPath="characterWithPronunciationsString"
                                        itemValuePath="id"
                                        items={pronunciationDtos || []}
                                        renderer={item =>
                                            <div>{item.item.id + ' - ' + item.item.characterWithPronunciationsString}</div>}
                                    />
                                );
                            },
                        },
                        toPronunciationId: {
                            label: 'Phát âm đích',
                            renderer: ({field}) => {
                                return (
                                    <ComboBox
                                        {...field}
                                        label="Phát âm đích"
                                        itemLabelPath="characterWithPronunciationsString"
                                        itemValuePath="id"
                                        items={pronunciationDtos || []}
                                        renderer={item =>
                                            <div>{item.item.id + ' - ' + item.item.characterWithPronunciationsString}</div>}
                                    />
                                );
                            }
                        }
                    }}
                    hiddenFields={['fromPronunciationString', 'toPronunciationString']}
                />
            </div>
        </div>
    );
};

const MeaningTabContent = () => {
    const [explanationTrigger, setExplanationTrigger] = useState(false);
    const [explanationGroupTrigger, setExplanationGroupTrigger] = useState(false);

    const explanationGridRef = useRef<any>(null);
    const meaningGridRef = useRef<any>(null);
    const meaningExplanationGridRef = useRef<any>(null);

    const [selectedExplanation, setSelectedExplanation] = useState<Explanation | undefined | null>(null);
    const [selectedExplanationDto, setSelectedExplanationDto] = useState<ExplanationDto | undefined | null>(null);
    const [selectedMeaning, setSelectedMeaning] = useState<Meaning | undefined | null>(null);
    const [selectedMeaningDto, setSelectedMeaningDto] = useState<MeaningDto | undefined | null>(null);
    const [selectedMeaningExplanation, setSelectedMeaningExplanation] = useState<MeaningExplanation | undefined | null>(null);
    const [selectedMeaningExplanationDto, setSelectedMeaningExplanationDto] = useState<MeaningExplanationDto | undefined | null>(null);

    const [explanations, setExplanations] = useState<Explanation[]>([]);
    const [meaningExplanations, setMeaningExplanations] = useState<MeaningExplanation[]>([]);
    const [meanings, setMeanings] = useState<Meaning[]>([]);

    useEffect(() => {
        ExplanationService.findAll().then(data => setExplanations((data || []).filter(e => e !== undefined) as Explanation[]));
        MeaningService.findAll().then(data => setMeanings((data || []).filter(m => m !== undefined) as Meaning[]));
    }, [explanationTrigger, explanationGroupTrigger]);

    // Load MeaningExplanations when selectedMeaning changes
    useEffect(() => {
        if (selectedMeaning?.id) {
            MeaningExplanationService.findByMeaning(selectedMeaning)
                .then(data => data?.filter(me => me !== undefined))
                .then(data => {
                    if (data) setMeaningExplanations(data);
                })
                .catch((error: any) => console.error('Error loading MeaningExplanations:', error));
        } else {
            setMeaningExplanations([]);
        }
    }, [selectedMeaning]);

    useEffect(() => {
        EntityMapper.toExplanationDto(selectedExplanation ?? undefined).then(dto => setSelectedExplanationDto(dto))
            .catch(error => console.error('Error mapping Explanation to DTO:', error));
    }, [selectedExplanation]);

    useEffect(() => {
        EntityMapper.toMeaningDto(selectedMeaning ?? undefined).then(dto => setSelectedMeaningDto(dto))
            .catch(error => console.error('Error mapping Meaning to DTO:', error));
    }, [selectedMeaning]);

    useEffect(() => {
        EntityMapper.toMeaningExplanationDto(selectedMeaningExplanation ?? undefined).then(dto => setSelectedMeaningExplanationDto(dto))
            .catch(error => console.error('Error mapping MeaningExplanation to DTO:', error));
    }, [selectedMeaningExplanation]);

    // Auto-set meaningId when selectedMeaning changes
    useEffect(() => {
        if (selectedMeaning?.id) {
            setSelectedMeaningExplanationDto({
                meaningId: selectedMeaning.id,
                explanationId: undefined
            });
        } else {
            setSelectedMeaningExplanationDto(null);
        }
    }, [selectedMeaning]);

    const deleteExplanationRenderer = ({item}: {item: Explanation}) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await ExplanationEndpoint.delete(item.id);
                    explanationGridRef.current?.refresh();
                    if (selectedExplanation?.id === item.id) {
                        setSelectedExplanation(null);
                    }
                    setExplanationTrigger(!explanationTrigger);
                    meaningGridRef.current?.refresh();
                } catch (error) {
                    console.error('Error deleting Explanation:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const deleteMeaningRenderer = ({item}: {item: Meaning}) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await MeaningEndpoint.delete(item.id);
                    meaningGridRef.current?.refresh();
                    if (selectedMeaning?.id === item.id) {
                        setSelectedMeaning(null);
                    }
                } catch (error) {
                    console.error('Error deleting Meaning:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const deleteMeaningExplanationRenderer = ({item}: {item: MeaningExplanation}) => {
        const handleDelete = async () => {
            if (item.id?.meaningId && item.id?.explanationId) {
                try {
                    await MeaningExplanationEndpoint.delete(item.id.meaningId, item.id.explanationId);
                    // Reload the filtered list
                    if (selectedMeaning?.id) {
                        MeaningExplanationEndpoint.findByMeaningId(selectedMeaning.id)
                            .then(async (dtos: any) => {
                                EntityMapper.toMeaningExplanationDtoList(dtos)
                                    .then(data => data?.filter(me => me !== undefined) as MeaningExplanation[])
                                    .then(data => setMeaningExplanations(data));
                            })
                            .catch((error: any) => console.error('Error reloading MeaningExplanations:', error));
                    }
                    if (selectedMeaningExplanation?.id?.meaningId === item.id?.meaningId &&
                        selectedMeaningExplanation?.id?.explanationId === item.id?.explanationId) {
                        setSelectedMeaningExplanation(null);
                    }
                    setExplanationGroupTrigger(!explanationGroupTrigger);
                } catch (error) {
                    console.error('Error deleting MeaningExplanation:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    return (
        <div
            style={{
                display: 'grid',
                gridTemplateColumns: '2fr 1fr 2fr',
                gridGap: '20px',
            }}
        >
            <div>
                <h3>Giải nghĩa</h3>
                <AutoGrid
                    service={ExplanationService}
                    ref={explanationGridRef}
                    model={ExplanationModel}
                    selectedItems={[selectedExplanation]}
                    onActiveItemChanged={i => setSelectedExplanation(i.detail.value)}
                    customColumns={[
                        <GridColumn
                            header="Xóa"
                            renderer={deleteExplanationRenderer}
                        />
                    ]}
                >

                </AutoGrid>
                <AutoForm
                    service={ExplanationEndpoint}
                    model={ExplanationDtoModel}
                    item={selectedExplanationDto}
                    onSubmitSuccess={() => {
                        setSelectedExplanation(null);
                        explanationGridRef.current?.refresh();
                        setExplanationTrigger(!explanationTrigger);
                    }}
                    onSubmitError={error => window.alert('Lỗi lưu giải nghĩa: ' + error.error.message)}
                />
            </div>
            <div>
                <h3>Ý nghĩa</h3>
                <AutoGrid
                    service={MeaningService}
                    ref={meaningGridRef}
                    model={MeaningModel}
                    selectedItems={[selectedMeaning]}
                    onActiveItemChanged={i => setSelectedMeaning(i.detail.value)}
                    columnOptions={{
                        origin: {
                            header: 'Nguồn gốc',
                            path: 'origin.explanationsString',
                        },
                        explanationsString: {
                            header: 'Nhóm giải nghĩa',
                            filterable: false,
                            sortable: false,
                        }
                    }}
                    customColumns={[
                        <GridColumn
                            header="Xóa"
                            renderer={deleteMeaningRenderer}
                        />
                    ]}
                    hiddenColumns={['origin']}
                >
                </AutoGrid>
                <AutoForm
                    service={MeaningEndpoint}
                    model={MeaningDtoModel}
                    item={selectedMeaningDto}
                    onSubmitSuccess={() => {
                        setSelectedMeaning(null);
                        meaningGridRef.current?.refresh();
                    }}
                    onSubmitError={error => window.alert('Lỗi lưu ý nghĩa: ' + error.error.message)}
                    hiddenFields={['explanationsString', 'origin']}
                    fieldOptions={{
                        originId: {
                            label: 'Ý nghĩa nguồn gốc',
                            renderer: ({field}) => (
                                <ComboBox
                                    label='Ý nghĩa nguồn gốc'
                                    itemLabelPath='explanationsString'
                                    itemValuePath='id'
                                    items={meanings || []}
                                    {...field}
                                    renderer={item =>
                                        <span>{item.item.id + ' - ' + item.item.explanationsString}</span>}
                                />
                            )
                        }
                    }}
                />
            </div>
            <div>
                <h3>Nhóm giải nghĩa</h3>
                {selectedMeaning ? (
                    <>
                        <p style={{ fontSize: '14px', color: '#666', marginBottom: '10px' }}>
                            Ý nghĩa đã chọn: {selectedMeaning.id} - {selectedMeaning.explanationsString}
                        </p>
                        <AutoGrid
                            service={MeaningExplanationService}
                            ref={meaningExplanationGridRef}
                            model={MeaningExplanationModel}
                            items={meaningExplanations}
                            selectedItems={[selectedMeaningExplanation]}
                            onActiveItemChanged={i => setSelectedMeaningExplanation(i.detail.value)}
                            columnOptions={{
                                id: { path: 'id.explanationId' },
                                meaning: { hidden: true },
                                explanation: {
                                    header: 'Giải nghĩa',
                                    path: 'explanation.description',
                                }
                            }}
                            customColumns={[
                                <GridColumn
                                    header="Xóa"
                                    renderer={deleteMeaningExplanationRenderer}
                                />
                            ]}
                        >
                        </AutoGrid>
                        <AutoForm
                            service={{
                                ...MeaningExplanationEndpoint,
                                delete: async (item: MeaningExplanationDto) => {
                                    if (item.meaningId && item.explanationId) {
                                        await MeaningExplanationEndpoint.delete(item.meaningId, item.explanationId);
                                    }
                                }
                            }}
                            model={MeaningExplanationDtoModel}
                            item={selectedMeaningExplanationDto}
                            onSubmitSuccess={() => {
                                setSelectedMeaningExplanation(null);
                                // Reload the filtered list
                                if (selectedMeaning?.id) {
                                    MeaningExplanationEndpoint.findByMeaningId(selectedMeaning.id)
                                        .then((dtos: any) => {
                                            EntityMapper.toMeaningExplanationDtoList(dtos)
                                                .then(data => data?.filter(me => me !== undefined))
                                                .then(data => setMeaningExplanations(data as MeaningExplanation[]));
                                        })
                                        .catch((error: any) => console.error('Error reloading MeaningExplanations:', error));
                                }
                                meaningGridRef.current?.refresh();
                                setExplanationGroupTrigger(!explanationGroupTrigger);
                            }}
                            onSubmitError={error => window.alert('Lỗi lưu nhóm giải nghĩa: ' + error.error.message)}
                            visibleFields={['explanationId']}
                            fieldOptions={{
                                explanationId: {
                                    label: 'Giải nghĩa',
                                    renderer: ({field}) => (
                                        <ComboBox
                                            label='Giải nghĩa'
                                            itemLabelPath='description'
                                            itemValuePath='id'
                                            items={explanations || []}
                                            {...field}
                                            renderer={item =>
                                        <span>{item.item.id + ' - ' + item.item.description}</span>}
                                        />
                                    )
                                }
                            }}
                        />
                    </>
                ) : (
                    <p style={{ fontSize: '14px', color: '#999', fontStyle: 'italic' }}>
                        Vui lòng chọn một ý nghĩa để quản lý nhóm giải nghĩa
                    </p>
                )}
            </div>
        </div>
    );
}

const EntityTabContent = () => {
    const entityGridRef = useRef<any>(null);
    const compositionGridRef = useRef<any>(null);
    const evolutionGridRef = useRef<any>(null);

    const [selectedEntity, setSelectedEntity] = useState<EntityX | undefined | null>(null);
    const [selectedEntityDto, setSelectedEntityDto] = useState<EntityDto | undefined | null>(null);
    const [selectedComposition, setSelectedComposition] = useState<EntityComposition | undefined | null>(null);
    const [selectedCompositionDto, setSelectedCompositionDto] = useState<EntityCompositionDto | undefined | null>(null);
    const [selectedEvolution, setSelectedEvolution] = useState<EntityEvolution | undefined | null>(null);
    const [selectedEvolutionDto, setSelectedEvolutionDto] = useState<EntityEvolutionDto | undefined | null>(null);

    const [entityDtos, setEntityDtos] = useState<EntityDto[]>([]);
    const [compositions, setCompositions] = useState<EntityComposition[]>([]);
    const [evolutions, setEvolutions] = useState<EntityEvolution[]>([]);
    const [structures, setStructures] = useState<StructureDto[]>([]);
    const [meanings, setMeanings] = useState<Meaning[]>([]);
    const [meaningsWithSuggests, setMeaningsWithSuggests] = useState<Meaning[]>([]);
    const [languages, setLanguages] = useState<Language[]>([]);
    const [pronunciations, setPronunciations] = useState<Pronunciation[]>([]);
    const [pronunciationDtos, setPronunciationDtos] = useState<PronunciationDto[]>([]);

    // Create stable service wrappers using useMemo
    const compositionService = useMemo(() => ({
        ...EntityCompositionEndpoint,
        list: async () => compositions
    }), [compositions]);

    const evolutionService = useMemo(() => ({
        ...EntityEvolutionEndpoint,
        list: async () => evolutions
    }), [evolutions]);

    useEffect(() => {
        EntityMapper.toEntityDto(selectedEntity ?? undefined).then(dto => setSelectedEntityDto(dto))
    }, [selectedEntity]);

    const refreshEntitiesTrigger = () => {
        EntityService.findAll()
            .then(data => data?.filter(entity => entity !== undefined))
            .then(data => EntityMapper.toEntityDtoList(data))
            .then(promises => Promise.all(promises || []))
            .then(dtos => dtos.filter(dto => dto !== undefined))
            .then(dtos => setEntityDtos(dtos as EntityDto[]));
    };
    useEffect(refreshEntitiesTrigger, []);

    // Map selected composition to DTO
    useEffect(() => {
        EntityMapper.toEntityCompositionDto(selectedComposition ?? undefined).then(dto => setSelectedCompositionDto(dto))
            .catch((error: any) => console.error('Error mapping EntityComposition to DTO:', error));
    }, [selectedComposition]);

    // Map selected evolution to DTO
    useEffect(() => {
        EntityMapper.toEntityEvolutionDto(selectedEvolution ?? undefined).then(dto => setSelectedEvolutionDto(dto))
            .catch((error: any) => console.error('Error mapping EntityEvolution to DTO:', error));
    }, [selectedEvolution]);

    // Load compositions when selectedEntity changes
    useEffect(() => {
        if (selectedEntity?.id) {
            EntityCompositionEndpoint.findByParentEntityId(selectedEntity.id)
                .then(async (dtos: any) => {
                    EntityMapper.toEntityCompositionList(dtos)
                        .then(data => data?.filter(c => c !== undefined))
                        .then(data => setCompositions(data as EntityComposition[]));
                })
                .catch((error: any) => console.error('Error loading EntityCompositions:', error));
        } else {
            setCompositions([]);
        }
    }, [selectedEntity]);

    // Load evolutions when selectedEntity changes
    useEffect(() => {
        if (selectedEntity?.id) {
            EntityEvolutionEndpoint.findByFromEntityId(selectedEntity.id)
                .then(async (dtos: any) => {
                    EntityMapper.toEntityEvolutionList(dtos)
                        .then(data => data?.filter(e => e !== undefined) as EntityEvolution[])
                        .then(data => setEvolutions(data));
                })
                .catch((error: any) => console.error('Error loading EntityEvolutions:', error));
        } else {
            setEvolutions([]);
        }
    }, [selectedEntity]);

    useEffect(() => {
        StructureEndpoint.findAll()
            .then(structures => structures?.filter(structure => structure !== undefined))
            .then(structures => setStructures(structures as StructureDto[]))
            .catch(error => console.error('Error fetching structures:', error));
    }, []);

    useEffect(() => {
        LanguageService.findAll().then(languages => {
            const tempLanguages: Language[] = [];
            languages?.forEach(language => {
                if (language) tempLanguages.push(language);
            });
            setLanguages(tempLanguages);
        });
    }, []);

    useEffect(() => {
        PronunciationService.findAll().then(pronunciations => {
            const tempPronunciations: Pronunciation[] = [];
            pronunciations?.forEach(pronunciation => {
                if (pronunciation) tempPronunciations.push(pronunciation);
            });
            setPronunciations(tempPronunciations);
        });
    }, []);

    useEffect(() => {
        EntityMapper.toPronunciationDtoList(pronunciations)
            .then(dtos => dtos?.filter(dto => dto !== undefined))
            .then(dtos => dtos as PronunciationDto[])
            .then(dtos => setPronunciationDtos(dtos))
            .catch(error => console.error('Error mapping Pronunciations to DTOs:', error));
    }, [pronunciations]);

    useEffect(() => {
        MeaningService.findAll().then(meanings => {
            const tempMeanings: Meaning[] = [];
            meanings?.forEach(meaning => {
                if (meaning) tempMeanings.push(meaning);
            });
            setMeanings(tempMeanings);
        });
    }, []);

    // Auto-set parentEntityId when selectedEntity changes
    useEffect(() => {
        if (selectedEntity?.id) {
            setSelectedCompositionDto({
                parentEntityId: selectedEntity.id,
                childEntityId: undefined,
                childEntity: undefined,
                position: undefined
            });
        } else {
            setSelectedCompositionDto(null);
        }
    }, [selectedEntity]);

    // Auto-set fromEntityId when selectedEntity changes
    useEffect(() => {
        if (selectedEntity?.id) {
            setSelectedEvolutionDto({
                fromEntityId: selectedEntity.id,
                toEntityId: undefined,
                fromEntity: undefined,
                description: undefined
            });
        } else {
            setSelectedEvolutionDto(null);
        }
    }, [selectedEntity]);

    const deleteEntityRenderer = ({item}: {item: EntityDto}) => {
        const handleDelete = async () => {
            if (item.id) {
                try {
                    await EntityEndpoint.delete(item.id);
                    // Reload entities list
                    // EntityService.findAll().then(data => setEntities((data || []).filter(e => e !== undefined)));
                    entityGridRef.current?.refresh();
                    if (selectedEntity?.id === item.id) {
                        setSelectedEntity(null);
                    }
                } catch (error) {
                    console.error('Error deleting Entity:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const deleteCompositionRenderer = ({item}: {item: EntityComposition}) => {
        const handleDelete = async () => {
            if (item.id?.parentEntityId && item.id?.childEntityId !== undefined && item.id?.position !== undefined) {
                try {
                    await EntityCompositionEndpoint.deleteByIds(item.id.parentEntityId, item.id.childEntityId, Number(item.id.position));
                    // Reload the filtered list
                    if (selectedEntity?.id) {
                        EntityCompositionEndpoint.findByParentEntityId(selectedEntity.id)
                            .then(async (dtos: any) => {
                                EntityMapper.toEntityCompositionList(dtos)
                                    .then(data => data?.filter(c => c !== undefined) as EntityComposition[])
                                    .then(data => setCompositions(data));
                            })
                            .catch((error: any) => console.error('Error reloading EntityCompositions:', error));
                    }
                    if (selectedComposition?.id?.parentEntityId === item.id?.parentEntityId &&
                        selectedComposition?.id?.childEntityId === item.id?.childEntityId &&
                        selectedComposition?.id?.position === item.id?.position) {
                        setSelectedComposition(null);
                    }
                } catch (error) {
                    console.error('Error deleting EntityComposition:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    const deleteEvolutionRenderer = ({item}: {item: EntityEvolution}) => {
        const handleDelete = async () => {
            if (item.id?.fromEntityId && item.id?.toEntityId) {
                try {
                    await EntityEvolutionEndpoint.deleteByEachId(item.id.fromEntityId, item.id.toEntityId);
                    // Reload the filtered list
                    if (selectedEntity?.id) {
                        EntityEvolutionEndpoint.findByFromEntityId(selectedEntity.id)
                            .then(async (dtos: any) => {
                                EntityMapper.toEntityEvolutionList(dtos)
                                    .then(data => data?.filter(e => e !== undefined) as EntityEvolution[])
                                    .then(data => setEvolutions(data));
                            })
                            .catch((error: any) => console.error('Error reloading EntityEvolutions:', error));
                    }
                    if (selectedEvolution?.id?.fromEntityId === item.id?.fromEntityId &&
                        selectedEvolution?.id?.toEntityId === item.id?.toEntityId) {
                        setSelectedEvolution(null);
                    }
                } catch (error) {
                    console.error('Error deleting EntityEvolution:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };

    return (
        <div
            style={{
                display: 'grid',
                gridTemplateColumns: '3fr 1fr 1fr',
                gridGap: '20px',
            }}
        >
            <div>
                <h3>Thực thể</h3>
                <AutoGrid
                    service={EntityService}
                    ref={entityGridRef}
                    model={EntityXModel}
                    selectedItems={[selectedEntity]}
                    onActiveItemChanged={i => setSelectedEntity(i.detail.value)}
                    columnOptions={{
                        id: {
                            filterable: false,
                        },
                        structure: {
                            path: 'structure.characterString',
                        },
                        pronunciation: {
                            path: 'pronunciation.string',
                        },
                        meaning: {
                            path: 'meaning.explanationsString',
                            width: '200px',
                        },
                        language: {
                            path: 'language.abbreviation',
                        },
                    }}
                    customColumns={[
                        <GridColumn
                            header="Xóa"
                            renderer={deleteEntityRenderer}
                        />
                    ]}
                    hiddenColumns={['hnomString', 'qnguString', 'explanationsString', 'pronunciationString', 'characterString', 'structureId']}
                />
                <AutoForm
                    service={EntityEndpoint}
                    model={EntityDtoModel}
                    item={selectedEntityDto}
                    onSubmitSuccess={() => {
                        setSelectedEntity(null);
                        refreshEntitiesTrigger();
                        entityGridRef.current?.refresh();
                        //o day
                    }}
                    onSubmitError={error => window.alert('Lỗi lưu thực thể: ' + error.error.message)}
                    hiddenFields={['hnomString', 'qnguString', 'explanationsString']}
                    fieldOptions={{
                        structureId: {
                            renderer: ({field}) => (
                                <ComboBox
                                    label='Cấu tạo'
                                    items={structures}
                                    renderer={(item) =>
                                        (<span>{item.item.id + ' - ' + (item.item.characterWithPronunciationsString || '[Không có ký tự]')}</span>)}
                                    itemValuePath='id'
                                    itemLabelPath='characterWithPronunciationsString'
                                    {...field}
                                />
                            ),
                        },
                        languageId: {
                            renderer: ({field}) => (
                                <ComboBox
                                    label='Ngôn ngữ'
                                    items={languages}
                                    renderer={(item) =>
                                        (<span>{item.item.id + ' - ' + item.item.abbreviation}</span>)}
                                    itemValuePath= 'id'
                                    itemLabelPath= 'abbreviation'
                                    {...field}
                                />
                            ),
                        },
                        pronunciationId: {
                            renderer: ({field}) => (
                                <ComboBox
                                    label='Phát âm'
                                    items={pronunciationDtos}
                                    renderer={(item) =>
                                        (<span>{item.item.id + ' - ' + item.item.characterWithPronunciationsString}</span>)}
                                    itemValuePath= 'id'
                                    itemLabelPath= 'characterWithPronunciationsString'
                                    {...field}
                                    onValueChanged={(e) => {
                                        MeaningService.findByPronunciationId(Number(e.detail.value))
                                            .then(data => data?.filter(meaning => meaning !== undefined))
                                            .then(data => {
                                                if (data) {
                                                    let tempMeanings = data;
                                                    tempMeanings.push(...meanings);
                                                    setMeaningsWithSuggests(tempMeanings);
                                                }
                                            })
                                            .catch(e => console.error('Error fetching meanings for pronunciation:', e));
                                    }}
                                />
                            ),
                        },
                        meaningId: {
                            renderer: ({field}) => (
                                <ComboBox
                                    label='Ý nghĩa'
                                    items={meaningsWithSuggests}
                                    renderer={(item) =>
                                        (<span>{item.item.id + ' - ' + item.item.explanationsString}</span>)}
                                    itemValuePath='id'
                                    itemLabelPath='explanationsString'
                                    {...field}
                                />
                            ),
                        }
                    }}
                />
            </div>
            <div>
                <h3>Cấu tạo thực thể</h3>
                {(selectedEntity && selectedEntity.compound) ? (
                    <>
                        <p style={{ fontSize: '14px', color: '#666', marginBottom: '10px' }}>
                            Thực thể đã chọn: {selectedEntity.id}
                        </p>
                        <AutoGrid
                            service={compositionService}
                            ref={compositionGridRef}
                            model={EntityCompositionModel}
                            items={compositions}
                            selectedItems={[selectedComposition]}
                            onActiveItemChanged={i => setSelectedComposition(i.detail.value)}
                            columnOptions={{
                                id: { hidden: true },
                                parentEntity: { hidden: true },
                                childEntity: {
                                    header: 'Thực thể con',
                                    path: 'childEntity.id',
                                }
                            }}
                            customColumns={[
                                <GridColumn
                                    header="Xóa"
                                    renderer={deleteCompositionRenderer}
                                />
                            ]}
                        />
                        <AutoForm
                            service={{
                                ...EntityCompositionEndpoint,
                                save: async (item) => {
                                    if (!item) return undefined;
                                    return await EntityCompositionEndpoint.saveByIds(selectedEntity?.id, item.childEntityId, item.position);
                                }
                            }}
                            model={EntityCompositionDtoModel}
                            item={selectedCompositionDto}
                            onSubmitSuccess={() => {
                                setSelectedComposition(null);
                                // Reload the filtered list
                                if (selectedEntity?.id) {
                                    EntityCompositionEndpoint.findByParentEntityId(selectedEntity.id)
                                        .then(async (dtos: any) => {
                                            EntityMapper.toEntityCompositionList(dtos)
                                                .then(dtos => dtos?.filter(c => c !== undefined))
                                                .then(c => setCompositions(c as EntityComposition[]));
                                        })
                                        .catch((error: any) => console.error('Error reloading EntityCompositions:', error));
                                }
                            }}
                            onSubmitError={error => window.alert('Lỗi lưu cấu tạo: ' + error.error.message)}
                            visibleFields={['childEntityId', 'order', 'position']}
                            fieldOptions={{
                                childEntityId: {
                                    label: 'Thực thể con',
                                    renderer: ({field}) => (
                                        <ComboBox
                                            label='Thực thể con'
                                            itemLabelPath='qnguString'
                                            itemValuePath='id'
                                            items={entityDtos || []}
                                            {...field}
                                            renderer={item =>
                                        <span>{item.item.id + ' - ' + item.item.hnomString + ' ' + item.item.qnguString + ' ' + item.item.explanationsString}</span>}
                                        />
                                    )
                                },
                                position: {
                                    label: 'Vị trí',
                                }
                            }}
                        />
                    </>
                ) : (
                    <p style={{ fontSize: '14px', color: '#999', fontStyle: 'italic' }}>
                        Vui lòng chọn một thực thể phức để quản lý cấu tạo
                    </p>
                )}
            </div>
            <div>
                <h3>Diễn biến thực thể</h3>
                {selectedEntity ? (
                    <>
                        <p style={{ fontSize: '14px', color: '#666', marginBottom: '10px' }}>
                            Thực thể đã chọn: {selectedEntity.id}
                        </p>
                        <AutoGrid
                            service={evolutionService}
                            ref={evolutionGridRef}
                            model={EntityEvolutionModel}
                            items={evolutions}
                            selectedItems={[selectedEvolution]}
                            onActiveItemChanged={i => setSelectedEvolution(i.detail.value)}
                            columnOptions={{
                                id: { hidden: true },
                                fromEntity: { hidden: true },
                                toEntity: {
                                    header: 'Thực thể đến',
                                    path: 'toEntity.pronunciationString',
                                },
                            }}
                            customColumns={[
                                <GridColumn
                                    header="Xóa"
                                    renderer={deleteEvolutionRenderer}
                                />
                            ]}
                        />
                        <AutoForm
                            service={{
                                ...EntityEvolutionEndpoint,
                                save: async (item: EntityEvolutionDto) => {
                                    return await EntityEvolutionEndpoint.saveByIds(selectedEntity?.id, item.toEntityId, item.description);
                                }
                            }}
                            model={EntityEvolutionDtoModel}
                            item={selectedEvolutionDto}
                            onSubmitSuccess={() => {
                                setSelectedEvolution(null);
                                // Reload the filtered list
                                if (selectedEntity?.id) {
                                    EntityEvolutionEndpoint.findByFromEntityId(selectedEntity.id)
                                        .then(async (dtos: any) => {
                                            EntityMapper.toEntityEvolutionList(dtos)
                                                .then(data => data?.filter(e => e !== undefined))
                                                .then(data => setEvolutions(data as EntityEvolution[]));
                                        })
                                        .catch((error: any) => console.error('Error reloading EntityEvolutions:', error));
                                }
                            }}
                            onSubmitError={error => window.alert('Lỗi lưu diễn biến: ' + error.error.message)}
                            visibleFields={['toEntityId', 'description']}
                            fieldOptions={{
                                toEntityId: {
                                    label: 'Đến thực thể',
                                    renderer: ({field}) => (
                                        <ComboBox
                                            label='Đến thực thể'
                                            itemLabelPath='qnguString'
                                            itemValuePath='id'
                                            items={entityDtos || []}
                                            {...field}
                                            renderer={item =>
                                        <span>{item.item.id + ' - ' + item.item.hnomString + ' ' + item.item.qnguString + ' ' + item.item.explanationsString}</span>}
                                        />
                                    )
                                }
                            }}
                        />
                    </>
                ) : (
                    <p style={{ fontSize: '14px', color: '#999', fontStyle: 'italic' }}>
                        Vui lòng chọn một thực thể để quản lý diễn biến
                    </p>
                )}
            </div>
        </div>
    );
};

const ExampleTabContent = () => {
    const [selectedExample, setSelectedExample] = useState<Example | undefined | null>(null);
    const [selectedExampleDto, setSelectedExampleDto] = useState<ExampleDto | undefined | null>(null);
    const [selectedExampleWord, setSelectedExampleWord] = useState<ExampleWord | undefined | null>(null);
    const [selectedExampleWordDto, setSelectedExampleWordDto] = useState<ExampleWordDto | undefined | null>(null);
    const exampleGridRef = useRef<any>(null);
    const exampleWordGridRef = useRef<any>(null);
    const [exampleWordDtos, setExampleWordDtos] = useState<ExampleWordDto[]>([]);
    const [sources, setSources] = useState<Source[]>([]);
    const [entities, setEntities] = useState<EntityDto[]>([]);

    useEffect(() => {
        EntityMapper.toExampleDto(selectedExample ?? undefined).then(dto => setSelectedExampleDto(dto))
            .catch((error: any) => console.error('Error mapping Example to ExampleDto:', error));
        setSelectedExampleWord(null);
    }, [selectedExample]);

    useEffect(() => {
        EntityMapper.toExampleWordDto(selectedExampleWord ?? undefined).then(dto => setSelectedExampleWordDto(dto))
            .catch((error: any) => console.error('Error mapping ExampleWord to ExampleWordDto:', error));
    }, [selectedExampleWord]);

    const exampleWordsTrigger = () => {
        if (selectedExample?.id) {
            ExampleWordEndpoint.findByExampleId(selectedExample.id)
                .then(data => data?.filter(exampleWord => exampleWord !== undefined))
                .then(data => setExampleWordDtos(data as ExampleWordDto[]))
                .catch(error => console.error('Error loading ExampleWords:', error));
        } else {
            setExampleWordDtos([]);
        }
    };

    useEffect(() => {
        exampleWordsTrigger();
    }, [selectedExample]);

    useEffect(() => {
        SourceService.findAll().then(sources => sources?.filter(source => source !== undefined))
            .then(sources => setSources(sources as Source[]))
            .catch(error => console.error('Error fetching sources:', error));
    }, []);

    useEffect(() => {
        EntityEndpoint.findAll().then(entities => entities?.filter(entity => entity !== undefined))
            .then(entities => setEntities(entities as EntityDto[]))
            .catch(error => console.error('Error fetching entities:', error));
    }, []);

    const deleteExampleRenderer = ({item}: {item: ExampleDto})  => {
            const handleDelete = async () => {
                if (item.id) {
                    try {
                        await ExampleEndpoint.delete(item.id);
                        // Reload examples list
                        exampleGridRef.current?.refresh();
                        exampleWordGridRef.current?.refresh();
                        if (selectedExample?.id === item.id) {
                            setSelectedExample(null);
                        }
                    } catch (error) {
                        console.error('Error deleting Example:', error);
                    }
                }
            };

            return (
                <button
                    onClick={handleDelete}
                    style={{
                        background: 'red',
                        color: 'white',
                        border: 'none',
                        padding: '5px 10px',
                        borderRadius: '4px',
                        cursor: 'pointer',
                    }}
                >
                    Xóa
                </button>
            );
        };
    const deleteExampleWordRenderer = ({item}: { item: ExampleWordDto }) => {
        const handleDelete = async () => {
            if (selectedExampleDto?.id && item.entityId && item.position) {
                try {
                    await ExampleWordEndpoint.deleteByEachId(selectedExampleDto?.id, item.entityId, item.position);
                    // Reload examples list
                    exampleGridRef.current?.refresh();
                    exampleWordsTrigger();
                    exampleWordGridRef.current?.refresh();
                    if (selectedExampleDto?.id === item.exampleId &&
                        selectedExampleWord?.exampleWordId?.entityId === item.entityId &&
                        selectedExampleWord?.exampleWordId?.position === item.position) {
                        setSelectedExampleWord(null);
                    }
                } catch (error) {
                    console.error('Error deleting ExampleWord:', error);
                }
            }
        };

        return (
            <button
                onClick={handleDelete}
                style={{
                    background: 'red',
                    color: 'white',
                    border: 'none',
                    padding: '5px 10px',
                    borderRadius: '4px',
                    cursor: 'pointer',
                }}
            >
                Xóa
            </button>
        );
    };
    const exampleWordDtoService = useMemo(() => ({
        async list(request: any, token?: any) {
            const exampleWords = await ExampleWordService.list(request, token);
            return EntityMapper.toExampleWordDtoList(exampleWords).then(data => data?.filter(ew => ew !== undefined))
                .then(data => data as ExampleWordDto[]);
        },
        async save(item: ExampleWordDto | undefined) {
            if (!item) return undefined;
            const exampleWord = await EntityMapper.toExampleWord(item);
            const saved = await ExampleWordService.save(exampleWord);
            return await EntityMapper.toExampleWordDto(saved);
        }
    }), []);


    return (
        <div
            style={{
                display: 'grid',
                gridTemplateColumns: '1fr 1fr',
                gridGap: '20px',
            }}
        >
            <div>
                <AutoGrid
                    service={ExampleService}
                    model={ExampleModel}
                    ref={exampleGridRef}
                    selectedItems={[selectedExample]}
                    onActiveItemChanged={(i) => setSelectedExample(i.detail.value)}
                    customColumns={[
                        <GridColumn
                            header="Xóa"
                            renderer={deleteExampleRenderer}
                        />,
                        <GridColumn
                            header='Nôm'
                            renderer={({item}) => <HnomStringByExampleIdComponent exampleId={item.id}/>}
                        />
                    ]}
                    columnOptions={{
                        source: {
                            title: 'Nguồn',
                            path: 'source.name',
                        },
                    }}
                />
                <AutoForm
                    service={ExampleEndpoint}
                    model={ExampleDtoModel}
                    item={selectedExampleDto || undefined}
                    onSubmitSuccess={() => {
                        setSelectedExample(null);
                        exampleGridRef.current?.refresh();
                    }}
                    onSubmitError={error => window.alert('Lỗi lưu ví dụ: ' + error.error.message)}
                    fieldOptions={{
                        sourceId: {
                            renderer: ({field}) => (
                                <ComboBox
                                    label='Nguồn'
                                    items={sources}
                                    itemValuePath='id'
                                    itemLabelPath='name'
                                    {...field}
                                />
                            )
                        },
                    }}
                    hiddenFields={['hnomString', 'qnguString', 'sourceName', 'sourceDescription']}
                />
            </div>
            <div>
                <AutoGrid
                    service={exampleWordDtoService}
                    model={ExampleWordDtoModel}
                    ref={exampleWordGridRef}
                    items={exampleWordDtos}
                    selectedItems={[selectedExampleWordDto]}
                    onActiveItemChanged={(i) => setSelectedExampleWordDto(i.detail.value)}
                    customColumns={[
                        <GridColumn
                            header='Xóa'
                            renderer={deleteExampleWordRenderer}
                        />
                    ]}
                    columnOptions={{
                        position: {
                            header: 'Vị trí',
                            filterable: false,
                        },
                        entity: {
                            path: 'entity.hnomString',
                        },
                    }}
                    hiddenColumns={['exampleId', 'example', 'entityId',]}
                />
                <AutoForm
                    service={{
                        ...ExampleWordEndpoint,
                        save: async (item: ExampleWordDto): Promise<ExampleWordDto | undefined> => {
                            if (selectedExample?.id && item.entityId && item.position) {
                                return await ExampleWordEndpoint.save(selectedExample.id, item.entityId, item.position);
                            }
                            return undefined;
                        }
                    }}
                    model={ExampleWordDtoModel}
                    item={selectedExampleWordDto || undefined}
                    onSubmitSuccess={() => {
                        setSelectedExampleWord(null);
                        exampleWordGridRef.current?.refresh();
                        exampleWordsTrigger();
                        exampleGridRef.current?.refresh();
                    }}
                    onSubmitError={error => window.alert('Lỗi lưu ví dụ-thực thể: ' + error.error.message)}
                    fieldOptions={{
                        entityId: {
                            label: 'Thực thể',
                            renderer: ({field}) => (
                                <ComboBox
                                    label='Thực thể'
                                    itemLabelPath='qnguString'
                                    itemValuePath='id'
                                    items={entities || []}
                                    {...field}
                                    renderer={item =>
                                        <span>{item.item.id + ' - ' + item.item.hnomString + ' ' + item.item.qnguString + ' ' + item.item.explanationsString}</span>}
                                />
                            )
                        },
                    }}
                    hiddenFields={['entity', 'exampleId']}
                />
            </div>
        </div>
    );
};

